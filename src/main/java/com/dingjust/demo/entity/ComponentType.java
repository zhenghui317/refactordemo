/*
 *
 *  Dingjust Order Services
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
 * @author chenwenyue
 * @version 2.0.0
 */
public enum ComponentType {
    /**
     * 进站
     */
    PULLIN,
    /**
     * 进站满载
     */
    INFULLLOAD,
    /**
     * 自动下放
     */
    AUTODOWNPASS,
    /**
     * 驱动提升-前
     */
    DRIVELIFTFRONT,
    /**
     * 队列满载
     */
    QUEUEFULLLOAD,
    /**
     * 手动下放
     */
    HANDDOWNPASS,
    /**
     * 驱动提升-后
     */
    DRIVELIFTAFTER,
    /**
     * 出站
     */
    OUTBOUND,
    /**
     * 驱动提升
     */
    DRIVELIFT,
    /**
     * 主轨控制
     */
    LINECONTROLLER,
    /**
     * 电机
     */
    MOTOR,
    /**
     * 绑定作业单读卡器
     */
    BINDINGORDER,
}
