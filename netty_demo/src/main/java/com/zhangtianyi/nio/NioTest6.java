package com.zhangtianyi.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName: NioTest6
 * @Description: NIO-buffer详解,buffer.slice
 * @author zhangtainyi
 * @date 2019/6/24 9:33
 *
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }
        buffer.position(2);
        buffer.limit(6);
        ByteBuffer sliceBuffer = buffer.slice();
        for (int i = 0; i < sliceBuffer.capacity(); ++i) {
            byte b = sliceBuffer.get();
            b *= 2;
            sliceBuffer.put(i, b);
        }
        //sliceBuffer和buffer的position和limit互不相干,是独立的
        System.out.println("buffer的position："+buffer.position());
        System.out.println("buffer的limit："+buffer.limit());
        System.out.println("sliceBuffer的position："+sliceBuffer.position());
        System.out.println("sliceBuffer的limit："+sliceBuffer.limit());

        buffer.position(0);
        buffer.limit(buffer.capacity());
        //sliceBuffer和buffer底层共享数据(底层数组)
        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }
    }
}
