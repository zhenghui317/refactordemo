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
package com.dingjust.demo.pojo.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * NextStackConfig dto.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class PreviousStackConfigDTO implements Serializable {
    private Long id;
    private StackDTO previousStack;
    private StackDTO currentStack;
    private StackDTO nextStack;
    private String description;

    public PreviousStackConfigDTO() {

    }
    public PreviousStackConfigDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StackDTO getPreviousStack() {
        return previousStack;
    }

    public void setPreviousStack(StackDTO previousStack) {
        this.previousStack = previousStack;
    }

    public StackDTO getCurrentStack() {
        return currentStack;
    }

    public void setCurrentStack(StackDTO currentStack) {
        this.currentStack = currentStack;
    }

    public StackDTO getNextStack() {
        return nextStack;
    }

    public void setNextStack(StackDTO nextStack) {
        this.nextStack = nextStack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
