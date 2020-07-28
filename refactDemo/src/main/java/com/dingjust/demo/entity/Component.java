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

import java.time.LocalDateTime;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("Component")
public class Component  {
    private String code;
    private String description;
    private String componentName;
    private Integer sequenceNumber;
    private ComponentStatus status;
    private ComponentType componentType;
    private IconType iconType;
    private EcuItfNo itfNo;
    private LocalDateTime updateTime;

    public Component() {
        super();
    }

    public Component(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComponentStatus getStatus() {
        return status;
    }

    public void setStatus(ComponentStatus status) {
        this.status = status;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public IconType getIconType() {
        return iconType;
    }

    public void setIconType(IconType iconType) {
        this.iconType = iconType;
    }

    public EcuItfNo getItfNo() {
        return itfNo;
    }

    public void setItfNo(EcuItfNo itfNo) {
        this.itfNo = itfNo;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
