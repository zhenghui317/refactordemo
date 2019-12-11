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
import com.dingjust.demo.enums.StackTypeEnum;
import org.apache.ibatis.type.Alias;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("Stack")
public class Stack  {
    private String code;
    private String description;
    private Integer sequenceNumber;
    private StackStatus status;
    private Double length;
    private Double width;
    private Integer maxWip;
    private StackTypeEnum stackType;
    private Stack mainRoadStack;
    private Ecu ecu;

    public Stack() {
        super();
    }

    public Stack(Long id) {
        super(id);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StackStatus getStatus() {
        return status;
    }

    public void setStatus(StackStatus status) {
        this.status = status;
    }

    public Integer getMaxWip() {
        return maxWip;
    }

    public void setMaxWip(Integer maxWip) {
        this.maxWip = maxWip;
    }

    public StackTypeEnum getStackType() {
        return stackType;
    }

    public void setStackType(StackType stackType) {
        this.stackType = stackType;
    }

    public Stack getMainRoadStack() {
        return mainRoadStack;
    }

    public void setMainRoadStack(Stack mainRoadStack) {
        this.mainRoadStack = mainRoadStack;
    }

    public Ecu getEcu() {
        return ecu;
    }

    public void setEcu(Ecu ecu) {
        this.ecu = ecu;
    }
}
