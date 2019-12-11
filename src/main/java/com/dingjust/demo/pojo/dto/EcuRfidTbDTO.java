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
 * EcuRfidTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class EcuRfidTbDTO implements Serializable {
    private Long id;
    private String rfidName;
    private Integer rfidNo;
    private LocalDateTime rfidTime;
    private String rfidAttr;
    private String rfidFlag;
    private String rfidBz;
    private EcuDTO ecu;
    private RfidAttributeDTO rfidAttribute;

    public EcuRfidTbDTO() {

    }

    public EcuRfidTbDTO(Long id) {
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

    public RfidAttributeDTO getRfidAttribute() {
        return rfidAttribute;
    }

    public void setRfidAttribute(RfidAttributeDTO rfidAttribute) {
        this.rfidAttribute = rfidAttribute;
    }
}
