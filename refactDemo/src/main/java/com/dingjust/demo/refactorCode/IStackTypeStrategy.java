package com.dingjust.demo.refactorCode;


import com.dingjust.demo.entity.Stack;
import com.dingjust.demo.entity.StackComponent;
import com.dingjust.demo.pojo.dto.EcuRfidTbDTO;
import com.dingjust.demo.socket.NettyClient;

public interface IStackTypeStrategy {
    void done(NettyClient nettyClient, EcuRfidTbDTO rfidTb, Stack stack, int data, StackComponent stackComponent);
}
