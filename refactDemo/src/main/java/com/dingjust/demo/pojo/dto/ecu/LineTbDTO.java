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
public class LineTbDTO implements Serializable {
    private String id;
    private String lineName;
    private String lineAttr;
    private String lineFlag;
    private LocalDateTime lineTime;
    private String lineBz;
    private Integer lineNo;

    public LineTbDTO() {

    }

    public LineTbDTO(String id) {
        this.id = id;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineAttr() {
        return lineAttr;
    }

    public void setLineAttr(String lineAttr) {
        this.lineAttr = lineAttr;
    }

    public String getLineFlag() {
        return lineFlag;
    }

    public void setLineFlag(String lineFlag) {
        this.lineFlag = lineFlag;
    }

    public LocalDateTime getLineTime() {
        return lineTime;
    }

    public void setLineTime(LocalDateTime lineTime) {
        this.lineTime = lineTime;
    }

    public String getLineBz() {
        return lineBz;
    }

    public void setLineBz(String lineBz) {
        this.lineBz = lineBz;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
