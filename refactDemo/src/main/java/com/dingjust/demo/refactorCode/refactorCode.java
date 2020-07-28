package com.dingjust.demo.refactorCode;

import com.dingjust.demo.entity.*;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.socket.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * 原来的代码
 */
@Slf4j
public class refactorCode {


    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private HandlerStackTypeFactory cardCategoryFactory;

    private Map<Integer, EcuRfidTbDTO> ecuRfidTbMap;
    /**
     * 当前代码主要是处理读卡器读到RFID数据
     * @param data
     * @param stackComponent
     */
    public void dealCardReader(int data, StackComponent stackComponent) throws Exception {
        Stack stack = stackComponent.getStack();
        if(stack.getStackType() == null){
            log.error("当前StackType为空");
            return;
        }
        EcuRfidTbDTO rfidTb = ecuRfidTbMap.get(data);
        IStackTypeStrategy cardCategoryStrategy = cardCategoryFactory.getStrategyByRequest(stack.getStackType());
        if(cardCategoryStrategy == null){
            throw new Exception("没有对应的策略");
        }
        cardCategoryStrategy.done(nettyClient,rfidTb, stack, data, stackComponent);

    }
}
