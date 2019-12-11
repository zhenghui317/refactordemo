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
public class RfidAttributeDTO implements Serializable {
    private Long id;
    private String orderType;
    private String priority;
    private LocalDateTime requiredDeliveryDate;
    private LocalDateTime actualStartDate;
    private EcuRfidTbDTO rfid;

    public RfidAttributeDTO() {

    }

    public RfidAttributeDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getRequiredDeliveryDate() {
        return requiredDeliveryDate;
    }

    public void setRequiredDeliveryDate(LocalDateTime requiredDeliveryDate) {
        this.requiredDeliveryDate = requiredDeliveryDate;
    }

    public LocalDateTime getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public EcuRfidTbDTO getRfid() {
        return rfid;
    }

    public void setRfid(EcuRfidTbDTO rfid) {
        this.rfid = rfid;
    }
}
