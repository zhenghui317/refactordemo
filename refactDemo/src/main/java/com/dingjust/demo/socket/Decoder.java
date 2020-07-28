package com.dingjust.demo.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created by dingjust on 2018/8/7.
 * 转换接收数据为16进制
 */
public class Decoder extends MessageToMessageDecoder<ByteBuf> {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        list.add(bytesToHexFun1(req));

    }

    public static String bytesToHexFun1(byte[] bytes) {

// 一个byte为8位，可用两个十六进制位标识

        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }
            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }
        return new String(buf);
    }
}
