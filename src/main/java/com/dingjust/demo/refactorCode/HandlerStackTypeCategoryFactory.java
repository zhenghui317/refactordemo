package com.dingjust.demo.refactorCode;

import com.dingjust.demo.enums.StackTypeEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据堆点类型返回对应的处理策略
 * @author zhenghui
 * @date  2019-11-20
 */
@Component
public class HandlerStackTypeCategoryFactory {
    //存放所有策略类的map
    public static Map<StackTypeEnum, IStackTypeCategoryStrategy> requestCategoryStrategyMap = new HashMap<>();

    public static void putStrategy(StackTypeEnum stackTypeEnum, IStackTypeCategoryStrategy strategy){
        requestCategoryStrategyMap.put(stackTypeEnum, strategy);
    }

    public IStackTypeCategoryStrategy getStrategyByRequest(StackTypeEnum stackTypeEnum){
        IStackTypeCategoryStrategy cardStrategy = requestCategoryStrategyMap.get(stackTypeEnum);
        if(cardStrategy == null){
            throw new IllegalArgumentException("没有对应的类型");
        }
        //获取对应的策略
        return cardStrategy;
    }
}

