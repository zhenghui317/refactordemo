package com.dingjust.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 读取yml配置文件中的信息
 * Created by dingjust on 2018/8/1.
 */
@Component
@Configuration
public class NettyConfig {
    private static int port;

    private static int execPort;

    private static String ip;

    public static int getPort() {
        return port;
    }

    @Value("${netty.port}")
    public void setPort(int port) {
        NettyConfig.port = port;
    }

    public static String getIp() {
        return ip;
    }

    @Value("${netty.ip}")
    public void setIp(String ip) {
        NettyConfig.ip = ip;
    }

    public static int getExecPort() {
        return execPort;
    }

    @Value("${netty.exec-port}")
    public void setExecPort(int execPort) {
        NettyConfig.execPort = execPort;
    }
}
