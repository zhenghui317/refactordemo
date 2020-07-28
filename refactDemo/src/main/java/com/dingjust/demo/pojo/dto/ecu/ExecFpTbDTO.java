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
 * EcuExecFpTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class ExecFpTbDTO implements Serializable {
    private String id;
    private String execFpNo;
    private ExecTbDTO exec;
    private Integer execFpFlag;
    private LocalDateTime execFpTime;

    public ExecFpTbDTO() {

    }

    public ExecFpTbDTO(String id) {
        this.id = id;
    }

    public ExecTbDTO getExec() {
        return exec;
    }

    public void setExec(ExecTbDTO exec) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
