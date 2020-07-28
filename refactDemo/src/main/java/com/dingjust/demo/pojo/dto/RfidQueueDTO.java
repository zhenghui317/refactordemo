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


import java.time.LocalDateTime;

/**
 * RfidQueue entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class RfidQueueDTO {
    private Long id;
    private StackDTO stack;
    private Integer rfid;
    private String type;
    private String status;
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StackDTO getStack() {
        return stack;
    }

    public void setStack(StackDTO stack) {
        this.stack = stack;
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
