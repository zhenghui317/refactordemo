/*
 *
 *  Dingjust Cloud Platform
 *
 *  Copyright (c) 2018 Dingjust
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Dingjust
 *  ("Confidential Information"). You shall not disclose such
 *  Confidential Information and shall use it only in accordance with the
 *  terms of the license agreement you entered into with Dingjust.
 *
 */
package com.dingjust.demo.entity;

import com.dingjust.data.core.domain.AbstractPersistable;
import org.apache.ibatis.type.Alias;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("PreviousStackConfig")
public class PreviousStackConfig  {
    private Stack currentStack;
    private Stack previousStack;
    private Stack nextStack;
    private String description;

    public PreviousStackConfig() {
        super();
    }

    public PreviousStackConfig(Long id) {
        super(id);
    }


    public Stack getCurrentStack() {
        return currentStack;
    }

    public void setCurrentStack(Stack currentStack) {
        this.currentStack = currentStack;
    }

    public Stack getPreviousStack() {
        return previousStack;
    }

    public void setPreviousStack(Stack previousStack) {
        this.previousStack = previousStack;
    }

    public Stack getNextStack() {
        return nextStack;
    }

    public void setNextStack(Stack nextStack) {
        this.nextStack = nextStack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
