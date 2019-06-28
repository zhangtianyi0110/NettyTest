package com.zhangtianyi.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
/**
 * @ClassName: NioTest13
 * @Description: java编解码
 * @author zhangtainyi
 * @date 2019/6/28 8:59
 *
 */
public class NioTest13 {
    public static void main(String[] args) throws Exception{
        String inputFile = "src/main/java/com/zhangtianyi/nio/resource/NioTest13_In.txt";
        String outputFile = "src/main/java/com/zhangtianyi/nio/resource/NioTest13_Out.txt";
        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long fileLenth = new File(inputFile).length();

        FileChannel inputChannel = inputRandomAccessFile.getChannel();
        FileChannel outputChannel = outputRandomAccessFile.getChannel();
        //内存映射文件
        MappedByteBuffer inputData = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLenth);

        System.out.println("*************************");
        Charset.availableCharsets().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
        System.out.println("*************************");


//        Charset charset = Charset.forName("Utf-8");
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder charsetDecoder = charset.newDecoder();//字节数组转字符串,解码
        CharsetEncoder charsetEncoder = charset.newEncoder();//字符串转字节数组,编码

        CharBuffer charBuffer = charsetDecoder.decode(inputData);
        System.out.println(new String(charBuffer.array()));
        for (int i = 0; i < charBuffer.array().length; i++) {
            System.out.println(charBuffer.array()[i]);
        }

        ByteBuffer outputData = charsetEncoder.encode(charBuffer);

        outputChannel.write(outputData);

        inputChannel.close();
        outputChannel.close();

    }
}
