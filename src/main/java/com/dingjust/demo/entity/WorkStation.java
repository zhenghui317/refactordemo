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
@Alias("WorkStation")
public class WorkStation  {
    private String code;
    private String description;
    private WorkStationStatus status;
    private Double length;
    private Double width;
    private Integer maxWip;
    private Stack stack;
    private WorkStationType type;

    public WorkStation() {
        super();
    }

    public WorkStation(Long id) {
        super(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public WorkStationStatus getStatus() {
        return status;
    }

    public void setStatus(WorkStationStatus status) {
        this.status = status;
    }

    public Integer getMaxWip() {
        return maxWip;
    }

    public void setMaxWip(Integer maxWip) {
        this.maxWip = maxWip;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public WorkStationType getType() {
        return type;
    }

    public void setType(WorkStationType type) {
        this.type = type;
    }
}
