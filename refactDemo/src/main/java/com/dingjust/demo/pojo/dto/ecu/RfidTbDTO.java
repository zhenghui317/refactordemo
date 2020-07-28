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
 * EcuRfidTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class RfidTbDTO implements Serializable {
    private String id;
    private String rfidName;
    private Integer rfidNo;
    private LocalDateTime rfidTime;
    private String rfidAttr;
    private String rfidFlag;
    private String rfidBz;

    public RfidTbDTO() {

    }

    public RfidTbDTO(String id) {
        this.id = id;
    }

    public String getRfidName() {
        return rfidName;
    }

    public void setRfidName(String rfidName) {
        this.rfidName = rfidName;
    }

    public Integer getRfidNo() {
        return rfidNo;
    }

    public void setRfidNo(Integer rfidNo) {
        this.rfidNo = rfidNo;
    }

    public LocalDateTime getRfidTime() {
        return rfidTime;
    }

    public void setRfidTime(LocalDateTime rfidTime) {
        this.rfidTime = rfidTime;
    }

    public String getRfidAttr() {
        return rfidAttr;
    }

    public void setRfidAttr(String rfidAttr) {
        this.rfidAttr = rfidAttr;
    }

    public String getRfidFlag() {
        return rfidFlag;
    }

    public void setRfidFlag(String rfidFlag) {
        this.rfidFlag = rfidFlag;
    }

    public String getRfidBz() {
        return rfidBz;
    }

    public void setRfidBz(String rfidBz) {
        this.rfidBz = rfidBz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
