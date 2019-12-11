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
 * SystemConfig entity.
 *
 * @author huangsonglin
 * @version 2.0.0
 */
@Alias("LineConfig")
public class LineConfig  {

     private Line line;
     private Double proportion;
     private String createTime;
     private String description;

    public LineConfig() {
        super();
    }

    public LineConfig(Long id) {
        super(id);
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription(){
       return description;
    }

    public void setDescription(String description){
       this.description = description;
    }
    }
