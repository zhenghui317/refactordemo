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

import org.apache.ibatis.type.Alias;

/**
 * Line entity.
 *
 * @author lichunfeng
 * @version 1.0
 */
@Alias("StackComponent")
public class StackComponent extends Component {
    private Stack stack;

    public StackComponent() {
        super();
    }

    public StackComponent(Long id) {
        super(id);
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }
}
