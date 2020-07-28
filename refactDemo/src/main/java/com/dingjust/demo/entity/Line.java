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
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("Line")
public class Line  {
    private String code;
    private Integer sequenceNumber;
    private RotatingDirection rotatingDirection;
    private Double length;
    private Double width;
    private ProductionLineType type;
    private Double speed;
    private Ecu ecu;
    private Line startLine;
    private Line endLine;
    private Double leftDistance;
    private Stack stack;
    private LineOperationStatus operationStatus;
    private Integer wipQty;
    private Integer pushrodQty;

    public Line() {
        super();
    }

    public Line(Long id) {
        super(id);
    }

    public Ecu getEcu() {
        return ecu;
    }

    public void setEcu(Ecu ecu) {
        this.ecu = ecu;
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

    public RotatingDirection getRotatingDirection() {
        return rotatingDirection;
    }

    public void setRotatingDirection(RotatingDirection rotatingDirection) {
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

    public ProductionLineType getType() {
        return type;
    }

    public void setType(ProductionLineType type) {
        this.type = type;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Line getStartLine() {
        return startLine;
    }

    public void setStartLine(Line startLine) {
        this.startLine = startLine;
    }

    public Line getEndLine() {
        return endLine;
    }

    public void setEndLine(Line endLine) {
        this.endLine = endLine;
    }

    public Double getLeftDistance() {
        return leftDistance;
    }

    public void setLeftDistance(Double leftDistance) {
        this.leftDistance = leftDistance;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public LineOperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(LineOperationStatus operationStatus) {
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
