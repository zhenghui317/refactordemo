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

import com.dingjust.data.core.domain.AbstractPersistable;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * HangerPlace entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("HangerPlace")
public class HangerPlace  {
    private EcuRfidTb rfid;
    private WorkStation workStation;
    private Line line;
    private LocalDateTime updateTime;

    public HangerPlace() {
        super();
    }

    public HangerPlace(Long id) {
        super(id);
    }

    public EcuRfidTb getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTb rfid) {
        this.rfid = rfid;
    }

    public WorkStation getWorkStation() {
        return workStation;
    }

    public void setWorkStation(WorkStation workStation) {
        this.workStation = workStation;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
