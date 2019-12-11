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
package com.dingjust.demo.entity;

import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * RfidQueue entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("LineRfid")
public class LineRfid {
    private Long id;
    private Line line;
    private Integer rfid;
    private LineRfidStatus status;
    private LocalDateTime updateTime;
    private LocalDateTime arrivedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    public LineRfidStatus getStatus() {
        return status;
    }

    public void setStatus(LineRfidStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(LocalDateTime arrivedTime) {
        this.arrivedTime = arrivedTime;
    }
}
