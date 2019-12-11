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
 * Ecu entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("Dispatching")
public class Dispatching  {
    private Stack startStack;
    private Stack endStack;
    private String lines;
    private String stacks;
    private DispatchingStatus status;
    private SubmitStatus submitStatus;
    private DispatchingType dispatchingType;
    private String workOrderNo;
    private EcuRfidTb rfid;
    private int no;
    private LocalDateTime updateTime;
    private LocalDateTime inBoundTime;
    private LocalDateTime outBoundTime;

    public Dispatching() {
        super();
    }

    public Dispatching(Long id) {
        super(id);
    }

    public Stack getStartStack() {
        return startStack;
    }

    public void setStartStack(Stack startStack) {
        this.startStack = startStack;
    }

    public Stack getEndStack() {
        return endStack;
    }

    public void setEndStack(Stack endStack) {
        this.endStack = endStack;
    }

    public String getStacks() {
        return stacks;
    }

    public void setStacks(String stacks) {
        this.stacks = stacks;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public DispatchingStatus getStatus() {
        return status;
    }

    public void setStatus(DispatchingStatus status) {
        this.status = status;
    }

    public SubmitStatus getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(SubmitStatus submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public EcuRfidTb getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTb rfid) {
        this.rfid = rfid;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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

    public DispatchingType getDispatchingType() {
        return dispatchingType;
    }

    public void setDispatchingType(DispatchingType dispatchingType) {
        this.dispatchingType = dispatchingType;
    }
}
