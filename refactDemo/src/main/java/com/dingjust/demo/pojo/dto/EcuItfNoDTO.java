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
 * EcuRfidTb entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
public class EcuItfNoDTO implements Serializable {
    private Long id;
    private Integer itfNo;
    private String code;
    private String description;
    private String name;
    private String itfBz;
    private String type;

    public EcuItfNoDTO() {

    }

    public EcuItfNoDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItfNo() {
        return itfNo;
    }

    public void setItfNo(Integer itfNo) {
        this.itfNo = itfNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItfBz() {
        return itfBz;
    }

    public void setItfBz(String itfBz) {
        this.itfBz = itfBz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
