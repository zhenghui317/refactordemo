/*
 *
 *  Dingjust Master Data Services
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

/**
 * 工位类型
 *
 * @author chenwenyue
 * @version 2.0.0
 */
public enum WorkStationType {
    /**
     * 裁剪
     */
    CUTTING,
    /**
     * 上吊挂
     */
    HANGING,
    /**
     * 缝制
     */
    STITCHING,
    /**
     * 质检
     */
    QUALITY,
    /**
     * 包装
     */
    PACK,
    /**
     * 印染
     */
    PRINTING
}
