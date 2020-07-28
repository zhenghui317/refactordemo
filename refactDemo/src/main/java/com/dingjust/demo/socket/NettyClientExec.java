package com.dingjust.demo.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dingjust on 2018/8/6.
 */
@Component
public class NettyClientExec {

    public static NettyClientExec nettyClientExec;


    @PostConstruct
    public void init() {
        nettyClientExec = this;
    }


    private Logger logger = LoggerFactory.getLogger(getClass());

    private static ChannelFuture future;
    private static Bootstrap b;
    private String content;

    public void connect(String host, int port,String data) throws InterruptedException {
        content = data;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new FixedLengthFrameDecoder(28));
                            //ch.pipeline().addLast(new StringDecoder());
                            //ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast("encoder", new Encoder());
                            ch.pipeline().addLast("decoder", new Decoder());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            future = b.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            logger.info("client send message:{}", content);
            ctx.writeAndFlush(content);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            try {
                if (msg == null || msg == "") {
                    logger.info("接收数据为空");
                    return;
                }
                String serverMsg = msg.toString();
                ctx.close();
            } catch (Exception e) {
                logger.info("信息处理错误错误" + e);
                return;
            }
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
