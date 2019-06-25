package com.zhangtianyi.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioTest12_Client {
    public static void main(String[] args) throws Exception{
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 5000));
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byteBuffer.put("hello,server! I am client.".getBytes(Charset.defaultCharset()));
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
//            byteBuffer.clear();
//            socketChannel.read(byteBuffer);
//            byteBuffer.flip();
//            byte[] bytes = new byte["hello,server! I am client.".getBytes().length];
//            byteBuffer.get(bytes);
//            System.out.println(new String(bytes,Charset.defaultCharset()));
//            socketChannel.close();
        }finally {
        }
    }
}
