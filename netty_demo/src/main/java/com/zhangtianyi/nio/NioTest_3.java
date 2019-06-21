package com.zhangtianyi.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
/**
 * @ClassName: NioTest_3
 * @Description: nio写文件
 * @author zhangtainyi
 * @date 2019/6/21 11:38
 *
 */
public class NioTest_3 {
    public static void main(String[] args) throws Exception{

        try(
                FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/zhangtianyi/nio/resource/NioTest_3.txt");
            ){
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byte[] bytes = "hello nio,ni hao".getBytes(Charset.defaultCharset());

            for (int i = 0; i < bytes.length; i++) {
                byteBuffer.put(bytes[i]);
            }

            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        }
    }
}
