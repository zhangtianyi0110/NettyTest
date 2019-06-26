package com.zhangtianyi.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @ClassName: NioClient
 * @Description: 客户端
 * @author zhangtainyi
 * @date 2019/6/26 17:39
 *
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);//注册

            socketChannel.connect(new InetSocketAddress("localhost", 8899));

            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    if(selectionKey.isConnectable()){
                        SocketChannel client = (SocketChannel)selectionKey.channel();//获取client对象
                        if(client.isConnectionPending()){//判断连接是否就绪
                            try {
                                client.finishConnect();//完成连接
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((LocalDateTime.now()+"已经连接").getBytes(Charset.defaultCharset()));
                                writeBuffer.flip();
                                client.write(writeBuffer);
                                //JDK5自带线程池，单个线程
                                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                                executorService.submit(()->{
                                    while (true){
                                        try {
                                            writeBuffer.clear();
                                            InputStreamReader inputStream = new InputStreamReader(System.in);
                                            BufferedReader br = new BufferedReader(inputStream);
                                            String sendMessage = br.readLine();
                                            writeBuffer.put(sendMessage.getBytes(Charset.defaultCharset()));
                                            writeBuffer.flip();
                                            client.write(writeBuffer);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            client.register(selector, SelectionKey.OP_READ);//注册read事件
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }
                    }else if(selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        try {
                            int count = client.read(readBuffer);
                            if(count > 0){
                                String receiveMessage = new String(readBuffer.array(), 0, count);
                                System.out.println(receiveMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                selectionKeys.clear();//循环结束清空selectionKeys
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
