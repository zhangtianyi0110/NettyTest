package com.zhangtianyi.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NioTest9
 * @Description: 内存映射文件MappedByteBuffer
 * @author zhangtainyi
 * @date 2019/6/24 16:00
 *
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/java/com/zhangtianyi/nio/resource/NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //映射模式，起始位置，映射长度
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte)'a');
        mappedByteBuffer.put(3, (byte)'b');
        randomAccessFile.close();
    }
}
