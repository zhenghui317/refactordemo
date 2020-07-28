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

/**
 * Pushrod entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("Pushrod")
public class Pushrod  {
    private String code;
    private Integer sequenceNumber;
    private Line line;
    private String rfid;

    public Pushrod() {
        super();
    }

    public Pushrod(Long id) {
        super(id);
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

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
