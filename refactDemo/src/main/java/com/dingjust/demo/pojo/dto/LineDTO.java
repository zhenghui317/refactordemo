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
public class LineDTO implements Serializable {
    private Long id;
    private String code;
    private Integer sequenceNumber;
    private String rotatingDirection;
    private Double length;
    private Double width;
    private String type;
    private Double speed;
    private EcuDTO ecu;
    private LineDTO startLine;
    private LineDTO endLine;
    private Double leftDistance;
    private StackDTO stack;
    private String operationStatus;
    private Integer wipQty;
    private Integer pushrodQty;

    public LineDTO() {

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

    public String getRotatingDirection() {
        return rotatingDirection;
    }

    public void setRotatingDirection(String rotatingDirection) {
        this.rotatingDirection = rotatingDirection;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public EcuDTO getEcu() {
        return ecu;
    }

    public void setEcu(EcuDTO ecu) {
        this.ecu = ecu;
    }

    public LineDTO getStartLine() {
        return startLine;
    }

    public void setStartLine(LineDTO startLine) {
        this.startLine = startLine;
    }

    public LineDTO getEndLine() {
        return endLine;
    }

    public void setEndLine(LineDTO endLine) {
        this.endLine = endLine;
    }

    public Double getLeftDistance() {
        return leftDistance;
    }

    public void setLeftDistance(Double leftDistance) {
        this.leftDistance = leftDistance;
    }

    public StackDTO getStack() {
        return stack;
    }

    public void setStack(StackDTO stack) {
        this.stack = stack;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Integer getWipQty() {
        return wipQty;
    }

    public void setWipQty(Integer wipQty) {
        this.wipQty = wipQty;
    }

    public Integer getPushrodQty() {
        return pushrodQty;
    }

    public void setPushrodQty(Integer pushrodQty) {
        this.pushrodQty = pushrodQty;
    }
}
