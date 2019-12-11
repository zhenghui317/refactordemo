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

/**
 * EcuConfigure entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class HangerDTO implements Serializable {
    private Long id;
    private String code;
    private Integer sequenceNumber;
    private EcuRfidTbDTO rfid;
    private Long currentPlace;
    private String placeType;
    private String currentPlaceCode;

    public HangerDTO() {

    }

    public HangerDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public EcuRfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTbDTO rfid) {
        this.rfid = rfid;
    }

    public Long getCurrentPlace() {
        return currentPlace;
    }

    public void setCurrentPlace(Long currentPlace) {
        this.currentPlace = currentPlace;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getCurrentPlaceCode() {
        return currentPlaceCode;
    }

    public void setCurrentPlaceCode(String currentPlaceCode) {
        this.currentPlaceCode = currentPlaceCode;
    }

}
