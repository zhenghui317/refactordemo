package com.dingjust.demo.socket;

import com.dingjust.c2m.mcs.core.domain.NettyConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * Created by dingjust on 2018/8/22.
 */
public class ConnectionListener implements ChannelFutureListener {
    private NettyClient nettyClient;

    public ConnectionListener(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {

            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                    System.err.println("服务端链接不上，开始重连操作...");
                    try {
                        nettyClient.connect(NettyConfig.getIp(), NettyConfig.getPort());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 5, TimeUnit.SECONDS);
        } else {
            System.err.println("服务端链接成功...");
        }
    }
}
