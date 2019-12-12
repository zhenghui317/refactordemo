package com.dingjust.demo.refactorCode.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.dingjust.demo.entity.*;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.socket.NettyClient;
import com.dingjust.demo.enums.StackTypeEnum;
import com.dingjust.demo.pojo.dto.WorkStationDTO;
import com.dingjust.demo.refactorCode.StackTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@StackTypeHandler(value = StackTypeEnum.LINE)
public class LineStackTypeServiceImpl extends AbstractStackTypeService {

    private List<WorkStationDTO> workStationDTOList;

    @Override
    public void done(NettyClient nettyClient, EcuRfidTbDTO rfidTb, Stack stack, int data, StackComponent stackComponent) {
        //产线上编号为的12的接口推送的读卡器信息是用来绑定作业单的
        if (StringUtils.equals(stackComponent.getComponentType().name(), ComponentType.BINDINGORDER.name())) {
            if (CollectionUtils.isNotEmpty(workStationDTOList)) {
                nettyClient.outBoundSupport.pushRfid(workStationDTOList.get(0), data);
            }
        } else {
            //产线读卡器信息推送过来的时候，去查询mes，是否有相应的作业单和该rfid绑定，如果没有，说明为空衣架，需要回收
            log.info("检测是否存在未绑定作业单的rfid");
            nettyClient.outBoundSupport.checkRfid(stack.getSequenceNumber(), data);
            log.info("检测该衣架是否在正常位置");
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
