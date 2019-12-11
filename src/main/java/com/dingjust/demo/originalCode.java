package com.dingjust.demo;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import com.dingjust.demo.entity.*;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.pojo.dto.WorkStationDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 原来的代码
 */
public class originalCode {


    public NettyClient nettyClient;

    private Map<Integer, EcuRfidTbDTO> ecuRfidTbMap;
    /**
     * 当前代码主要是处理读卡器读到RFID数据
     * @param data
     * @param stackComponent
     */
    public void dealCardReader(int data, StackComponent stackComponent) {
        Stack stack = stackComponent.getStack();
        EcuRfidTbDTO rfidTb = ecuRfidTbMap.get(data);
        if (stack.getStackType() != null && StringUtils.equals(stack.getStackType().name(), StackType.WORKSTATION.name())) {
            WorkStationDTO workStationDTO = workStationMap.get(stack.getSequenceNumber());
            if (rfidTb != null) {
                inOutStation(stackComponent, workStationDTO, rfidTb, data);
                if (workStationDTO != null && StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())
                        && StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.AUTODOWNPASS.name())) {
                    //回收站读卡器读取到衣架，删除该衣架历史记录
                    nettyClient.inBoundSupport.deleteHistoryDispatching(rfidTb);
                }
            } else {
                //如果rfid没有维护，需要初始维护rfid到mcs和ecu中
                if (workStationDTO != null && StringUtils.equals(workStationDTO.getType(), WorkStationType.HANGING.name())
                        && StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.PULLIN.name())) {
                    saveRfid(workStationDTO, data);
                }
            }
        } else if (StringUtils.equals(stack.getStackType().name(), StackType.NODE.name())) {
            //通过节点判断产线wip值，节点出站代表进入某一条产线
            if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.OUTBOUND.name())) {
                //获取本节点的上一条产线
                List<PreviousStackConfig> previousStackConfigList = previousStackConfigMap.get(stack.getSequenceNumber());
                Line previousLine = lineMap.get(previousStackConfigList.get(0).getPreviousStack().getSequenceNumber());
                //获取本节点的下一条产线
                List<PreviousStackConfig> nextStackConfigList = nextStackConfigMap.get(stack.getSequenceNumber());
                Line nextLine = lineMap.get(nextStackConfigList.get(0).getCurrentStack().getSequenceNumber());
                nettyClient.lineRfidStatusSupport.process(previousLine, nextLine, rfidTb, stack);
            } else if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.PULLIN.name())) {
                List<PreviousStackConfig> previousStackConfigList = previousStackConfigMap.get(stack.getSequenceNumber());
                Line previousLine = lineMap.get(previousStackConfigList.get(0).getPreviousStack().getSequenceNumber());

                List<PreviousStackConfig> nextStackConfigList = nextStackConfigMap.get(stack.getSequenceNumber());
                Line nextLine = lineMap.get(nextStackConfigList.get(0).getCurrentStack().getSequenceNumber());
                nettyClient.lineRfidStatusSupport.NodePullIn(previousLine, rfidTb, nextLine, stack);
                //判断下一个节点是否可以下发
                nettyClient.lineRfidStatusSupport.issueNextNodes(stack, rfidTb, nextStackConfigMap, lineMap, previousStackConfigMap);

            }
        } else if (stack.getStackType() != null && StringUtils.equals(stack.getStackType().name(), StackType.LINE.name())) {
            //产线上编号为的12的接口推送的读卡器信息是用来绑定作业单的
            if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.BINDINGORDER.name())) {
                if (CollectionUtils.isNotEmpty(workStationDTOList)) {
                    nettyClient.outBoundSupport.pushRfid(workStationDTOList.get(0), data);
                }
            } else {
                //产线读卡器信息推送过来的时候，去查询mes，是否有相应的作业单和该rfid绑定，如果没有，说明为空衣架，需要回收
                logger.info("检测是否存在未绑定作业单的rfid");
                nettyClient.outBoundSupport.checkRfid(stack.getSequenceNumber(), data);
                logger.info("检测该衣架是否在正常位置");
                nettyClient.rfidStatusSupport.process(stack, data);
                //如果是主轨读卡器
                Line line = lineMap.get(stack.getSequenceNumber());
                if (StringUtils.equals(line.getType().name(), ProductionLineType.PRINCIPALAXIS.name())) {
                    //判断下一个节点是否可以下发
                    nettyClient.lineRfidStatusSupport.issueNextNodes(stack, rfidTb, nextStackConfigMap, lineMap, previousStackConfigMap);
                }
            }
            //保存衣架当前位置
            nettyClient.inBoundSupport.saveHangerPlace(stack, rfidTb, stackConfigureDTOMap);
        }
    }
}
