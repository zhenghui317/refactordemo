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
 * EcuStackTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class EcuStatusLogDTO implements Serializable {
    private Long id;
    private String originalData;
    private String versionNo;
    private String stackNo;
    private String lineNo;
    private String itfNo;
    private String itfData;
    private LocalDateTime acceptTime;

    public EcuStatusLogDTO() {

    }

    public EcuStatusLogDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getStackNo() {
        return stackNo;
    }

    public void setStackNo(String stackNo) {
        this.stackNo = stackNo;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getItfNo() {
        return itfNo;
    }

    public void setItfNo(String itfNo) {
        this.itfNo = itfNo;
    }

    public String getItfData() {
        return itfData;
    }

    public void setItfData(String itfData) {
        this.itfData = itfData;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }
}
