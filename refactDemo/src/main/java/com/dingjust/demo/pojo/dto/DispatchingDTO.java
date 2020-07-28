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

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Pushrod entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class DispatchingDTO implements Serializable {
    private Long id;
    private StackDTO startStack;
    private StackDTO endStack;
    private String lines;
    private String stacks;
    private String status;
    private String submitStatus;
    private String dispatchingType;
    private String workOrderNo;
    private EcuRfidTbDTO rfid;
    private LocalDateTime updateTime;
    private LocalDateTime inBoundTime;
    private LocalDateTime outBoundTime;

    public DispatchingDTO() {

    }

    public DispatchingDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StackDTO getStartStack() {
        return startStack;
    }

    public void setStartStack(StackDTO startStack) {
        this.startStack = startStack;
    }

    public StackDTO getEndStack() {
        return endStack;
    }

    public void setEndStack(StackDTO endStack) {
        this.endStack = endStack;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public EcuRfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTbDTO rfid) {
        this.rfid = rfid;
    }

    public String getStacks() {
        return stacks;
    }

    public void setStacks(String stacks) {
        this.stacks = stacks;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public LocalDateTime getInBoundTime() {
        return inBoundTime;
    }

    public void setInBoundTime(LocalDateTime inBoundTime) {
        this.inBoundTime = inBoundTime;
    }

    public LocalDateTime getOutBoundTime() {
        return outBoundTime;
    }

    public void setOutBoundTime(LocalDateTime outBoundTime) {
        this.outBoundTime = outBoundTime;
    }

    public String getDispatchingType() {
        return dispatchingType;
    }

    public void setDispatchingType(String dispatchingType) {
        this.dispatchingType = dispatchingType;
    }
}
