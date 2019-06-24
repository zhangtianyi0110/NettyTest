package com.zhangtianyi.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NioTest4
 * @Description: nio文件通道
 * @author zhangtainyi
 * @date 2019/6/24 8:38
 *
 */
public class NioTest4 {
    public static void main(String[] args) {
        try(
                FileInputStream inputStream = new FileInputStream("src/main/java/com/zhangtianyi/nio/resource/input.txt");
                FileOutputStream outputStream = new FileOutputStream("src/main/java/com/zhangtianyi/nio/resource/output.txt");
                FileChannel inputChannel = inputStream.getChannel();
                FileChannel outputChannel = outputStream.getChannel();
                ){
            ByteBuffer buffer = ByteBuffer.allocate(1024);
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
