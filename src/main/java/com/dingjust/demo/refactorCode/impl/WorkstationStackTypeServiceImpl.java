package com.dingjust.demo.refactorCode.impl;

import com.dingjust.demo.entity.Stack;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.socket.NettyClient;
import com.dingjust.demo.entity.ComponentType;
import com.dingjust.demo.entity.StackComponent;
import com.dingjust.demo.entity.WorkStationType;
import com.dingjust.demo.enums.StackTypeEnum;
import com.dingjust.demo.pojo.dto.WorkStationDTO;
import com.dingjust.demo.refactorCode.StackTypeCategoryHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@StackTypeCategoryHandler(value = StackTypeEnum.WORKSTATION)
public class WorkstationStackTypeServiceImpl extends AbstractStackTypeCategoryService {
    @Override
    public void done(NettyClient nettyClient, EcuRfidTbDTO rfidTb, Stack stack, int data, StackComponent stackComponent) {
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
    }
}
