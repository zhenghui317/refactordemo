package com.dingjust.demo.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * Created by dingjust on 2018/8/7.
 * 转换发送数据，转换为byte
 */
public class Encoder extends MessageToByteEncoder<String> {

    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        if(msg != null) {
            byte[] bytes = toBytes(msg);
            out.writeBytes(bytes);
        }
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}
