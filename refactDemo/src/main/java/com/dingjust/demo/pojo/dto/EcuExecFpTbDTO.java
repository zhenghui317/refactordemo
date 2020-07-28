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
 * EcuExecFpTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class EcuExecFpTbDTO implements Serializable {
    private Long id;
    private String execFpNo;
    private EcuExecTbDTO exec;
    private Integer execFpFlag;
    private LocalDateTime execFpTime;
    private EcuDTO ecu;

    public EcuExecFpTbDTO() {

    }

    public EcuExecFpTbDTO(Long id) {
        this.id = id;
    }

    public EcuExecTbDTO getExec() {
        return exec;
    }

    public void setExec(EcuExecTbDTO exec) {
        this.exec = exec;
    }

    public Integer getExecFpFlag() {
        return execFpFlag;
    }

    public void setExecFpFlag(Integer execFpFlag) {
        this.execFpFlag = execFpFlag;
    }

    public LocalDateTime getExecFpTime() {
        return execFpTime;
    }

    public void setExecFpTime(LocalDateTime execFpTime) {
        this.execFpTime = execFpTime;
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

    public EcuDTO getEcu() {
        return ecu;
    }

    public void setEcu(EcuDTO ecu) {
        this.ecu = ecu;
    }
}
