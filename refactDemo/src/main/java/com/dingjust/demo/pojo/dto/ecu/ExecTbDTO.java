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
package com.dingjust.demo.pojo.dto.ecu;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class ExecTbDTO implements Serializable {
    private String id;
    private StackTbDTO stack;
    private RfidTbDTO rfid;
    private LineTbDTO line;
    private Integer execFlag;
    private String execAddTime;
    private LocalDateTime execModTime;
    private String execFpNo;

    public ExecTbDTO() {

    }

    public ExecTbDTO(String id) {
        this.id = id;
    }

    public StackTbDTO getStack() {
        return stack;
    }

    public void setStack(StackTbDTO stack) {
        this.stack = stack;
    }

    public RfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(RfidTbDTO rfid) {
        this.rfid = rfid;
    }

    public LineTbDTO getLine() {
        return line;
    }

    public void setLine(LineTbDTO line) {
        this.line = line;
    }

    public Integer getExecFlag() {
        return execFlag;
    }

    public void setExecFlag(Integer execFlag) {
        this.execFlag = execFlag;
    }

    public String getExecAddTime() {
        return execAddTime;
    }

    public void setExecAddTime(String execAddTime) {
        this.execAddTime = execAddTime;
    }

    public LocalDateTime getExecModTime() {
        return execModTime;
    }

    public void setExecModTime(LocalDateTime execModTime) {
        this.execModTime = execModTime;
    }

    public String getExecFpNo() {
        return execFpNo;
    }

    public void setExecFpNo(String execFpNo) {
        this.execFpNo = execFpNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
