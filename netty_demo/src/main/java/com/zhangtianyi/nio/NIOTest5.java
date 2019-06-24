package com.zhangtianyi.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName: NIOTest5
 * @Description: NIO-Buffer详解,ByteBuffer类型化的put和get方法
 * @author zhangtainyi
 * @date 2019/6/24 9:27
 *
 */
public class NIOTest5 {
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(500000000L);
        buffer.putDouble(14.123456);
        buffer.putChar('你');
        buffer.putShort((short)2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
