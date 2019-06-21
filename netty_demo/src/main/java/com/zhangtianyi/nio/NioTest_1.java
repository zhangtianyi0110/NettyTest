package com.zhangtianyi.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest_1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);//分配10个长度的缓冲区

        for (int i=0;i<5;i++){
            int randomNumber = new SecureRandom().nextInt(20);//生成20以内的随机数
            buffer.put(randomNumber);//放入缓冲区，写入
        }
        System.out.println("翻转前limit:"+buffer.limit());
        buffer.flip();//状态翻转，切换读写状态（标致变化）
        System.out.println("翻转后limit:"+buffer.limit());

        while (buffer.hasRemaining()){//循环取出，读取
            System.out.println("position:"+buffer.position());
            System.out.println("limit:"+buffer.limit());
            System.out.println("capacity:"+buffer.capacity());


            System.out.println(buffer.get());
        }
    }
}
