package com.zhangtianyi.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** 
 * @ClassName: NioTest8
 * @Description: 直接缓冲DirectBuffer
 * @author zhangtainyi
 * @date 2019/6/24 10:33
 *
 */
public class NioTest8 {
    public static void main(String[] args) {
        try(
                FileInputStream inputStream = new FileInputStream("src/main/java/com/zhangtianyi/nio/resource/input2.txt");
                FileOutputStream outputStream = new FileOutputStream("src/main/java/com/zhangtianyi/nio/resource/output2.txt");
                FileChannel inputChannel = inputStream.getChannel();
                FileChannel outputChannel = outputStream.getChannel();
        ){
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//此时返回的是DIrectBuffer对象
            while (true){
                buffer.clear();//必须清空,把position置为0
                int read = inputChannel.read(buffer);
                System.out.println("read:"+read);
                if(-1 == read){
                    break;
                }
                buffer.flip();
                outputChannel.write(buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
