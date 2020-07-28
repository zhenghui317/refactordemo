package com.dingjust.demo.refactorCode.impl;

import com.dingjust.demo.entity.*;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.socket.NettyClient;
import com.dingjust.demo.enums.StackTypeEnum;
import com.dingjust.demo.refactorCode.StackTypeHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@StackTypeHandler(value = StackTypeEnum.NODE)
public class NodeStackTypeServiceImpl extends AbstractStackTypeService {
    @Override
    public void done(NettyClient nettyClient, EcuRfidTbDTO rfidTb , Stack stack, int data, StackComponent stackComponent) {
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
    }
}
