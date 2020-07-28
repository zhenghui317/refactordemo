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

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class HangerPlaceDTO implements Serializable {
    private Long id;
    private EcuRfidTbDTO rfid;
    private WorkStationDTO workStation;
    private LineDTO line;
    private LocalDateTime updateTime;

    public HangerPlaceDTO() {

    }
    public HangerPlaceDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EcuRfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTbDTO rfid) {
        this.rfid = rfid;
    }

    public WorkStationDTO getWorkStation() {
        return workStation;
    }

    public void setWorkStation(WorkStationDTO workStation) {
        this.workStation = workStation;
    }

    public LineDTO getLine() {
        return line;
    }

    public void setLine(LineDTO line) {
        this.line = line;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
