package com.zhangtianyi.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName: NioTest7
 * @Description: NIO-buffer详解，任何一个buffer可以调用asReadOnlyBuffer()可以返回一个只读buffer，反过来不可以
 * @author zhangtainyi
 * @date 2019/6/24 9:54
 *
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readonlyBuffer.getClass());
        readonlyBuffer.position(0);
        System.out.println(readonlyBuffer.get());//会抛出异常
    }
}
