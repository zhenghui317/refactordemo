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
 * EcuStackTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class StackTbDTO implements Serializable {
    private String id;
    private String stackName;
    private String stackAttr;
    private String stackFlag;
    private LocalDateTime stackTime;
    private String stackBz;
    private Integer stackNo;
    private String stackQueue;
    private String stackFunc;
    private String stackElec1;
    private String stackElec2;

    public StackTbDTO() {

    }

    public StackTbDTO(String id) {
        this.id = id;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getStackAttr() {
        return stackAttr;
    }

    public void setStackAttr(String stackAttr) {
        this.stackAttr = stackAttr;
    }

    public String getStackFlag() {
        return stackFlag;
    }

    public void setStackFlag(String stackFlag) {
        this.stackFlag = stackFlag;
    }

    public LocalDateTime getStackTime() {
        return stackTime;
    }

    public void setStackTime(LocalDateTime stackTime) {
        this.stackTime = stackTime;
    }

    public String getStackBz() {
        return stackBz;
    }

    public void setStackBz(String stackBz) {
        this.stackBz = stackBz;
    }

    public Integer getStackNo() {
        return stackNo;
    }

    public void setStackNo(Integer stackNo) {
        this.stackNo = stackNo;
    }

    public String getStackQueue() {
        return stackQueue;
    }

    public void setStackQueue(String stackQueue) {
        this.stackQueue = stackQueue;
    }

    public String getStackFunc() {
        return stackFunc;
    }

    public void setStackFunc(String stackFunc) {
        this.stackFunc = stackFunc;
    }

    public String getStackElec1() {
        return stackElec1;
    }

    public void setStackElec1(String stackElec1) {
        this.stackElec1 = stackElec1;
    }

    public String getStackElec2() {
        return stackElec2;
    }

    public void setStackElec2(String stackElec2) {
        this.stackElec2 = stackElec2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
