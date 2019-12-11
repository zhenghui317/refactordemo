package com.dingjust.demo.refactorCode;

import com.dingjust.demo.enums.StackTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StackTypeCategoryHandler {
    StackTypeEnum value();
}
