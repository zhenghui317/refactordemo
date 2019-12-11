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
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class EcuExecTbDTO implements Serializable {
    private Long id;
    private EcuStackTbDTO stack;
    private EcuRfidTbDTO rfid;
    private EcuLineTbDTO line;
    private Integer execFlag;
    private String execAddTime;
    private LocalDateTime execModTime;
    private String execFpNo;
    private String issueStatus;
    private EcuDTO ecu;

    public EcuExecTbDTO() {

    }

    public EcuExecTbDTO(Long id) {
        this.id = id;
    }

    public EcuStackTbDTO getStack() {
        return stack;
    }

    public void setStack(EcuStackTbDTO stack) {
        this.stack = stack;
    }

    public EcuRfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTbDTO rfid) {
        this.rfid = rfid;
    }

    public EcuLineTbDTO getLine() {
        return line;
    }

    public void setLine(EcuLineTbDTO line) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public EcuDTO getEcu() {
        return ecu;
    }

    public void setEcu(EcuDTO ecu) {
        this.ecu = ecu;
    }
}
