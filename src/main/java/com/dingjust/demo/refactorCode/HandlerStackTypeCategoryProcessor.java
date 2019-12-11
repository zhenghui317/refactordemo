package com.dingjust.demo.refactorCode;

import com.dingjust.demo.enums.StackTypeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 策略核心功能,获取所有策略注解的类型
 * 并将对应的class初始化到HandlerRequestTypeContext中
 * @author zhenghui
 * @date  2019-11-20
 */
@Component
public class HandlerStackTypeCategoryProcessor implements ApplicationContextAware {

    /**
     * 获取所有的策略加入HandlerRequestCategoryFactory属性中
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取所有策略注解的Bean
        Map<String, Object> requestStrategyMap = applicationContext.getBeansWithAnnotation(StackTypeCategoryHandler.class);
        requestStrategyMap.forEach((k,v)->{
            Class<IStackTypeCategoryStrategy> strategyClass = (Class<IStackTypeCategoryStrategy>) v.getClass();
            StackTypeEnum type = strategyClass.getAnnotation(StackTypeCategoryHandler.class).value();
            IStackTypeCategoryStrategy requestStrategy = applicationContext.getBean(strategyClass);
            //加入map中,type作为key
            HandlerStackTypeCategoryFactory.putStrategy(type, requestStrategy);
        });
    }
}
