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
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class StackComponentDTO implements Serializable {
    private Long id;
    private String code;
    private String componentName;
    private String description;
    private Integer sequenceNumber;
    private String status;
    private String componentType;
    private StackDTO stack;
    private String iconType;
    private EcuItfNoDTO itfNo;

    public StackComponentDTO() {

    }
    public StackComponentDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public StackDTO getStack() {
        return stack;
    }

    public void setStack(StackDTO stack) {
        this.stack = stack;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public EcuItfNoDTO getItfNo() {
        return itfNo;
    }

    public void setItfNo(EcuItfNoDTO itfNo) {
        this.itfNo = itfNo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
