package com.zhangtianyi.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * @ClassName: NioTest_2
 * @Description: nio读文件
 * @author zhangtainyi
 * @date 2019/6/21 11:41
 *
 */
public class NioTest_2 {
    public static void main(String[] args) throws Exception {
        try (
                FileInputStream fileInputStream = new FileInputStream("src/main/java/com/zhangtianyi/nio/resource/NioTest_2.txt");
             ){
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(512);

            fileChannel.read(byteBuffer);

            byteBuffer.flip();

            while (byteBuffer.remaining()>0){
                byte b = byteBuffer.get();
                System.out.println("Character:"+(char)b);
            }

        }
    }
}
