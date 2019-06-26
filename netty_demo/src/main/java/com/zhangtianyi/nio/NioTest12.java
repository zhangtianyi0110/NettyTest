package com.zhangtianyi.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

//        System.out.println(SelectorProvider.provider().getClass());
//        System.out.println(sun.nio.ch.DefaultSelectorProvider.create().getClass());
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//调整阻塞模式，false不阻塞
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocketChannel.bind(address);//监听端口
            //注册accept事件到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口：" + ports[i]);
        }
        while (true){
            int numbers = selector.select();//获取所有selectKey键，阻塞
            System.out.println("number:" + numbers);//五个serverSocketChannel都注册了Accept，只有一个键

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys:" + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();//等待连接连上
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);//将连接注册到selector上，关注事件是读

                    iterator.remove();//事件用完必须要移除掉,不然会异常

                    System.out.println("获得客户端连接：" + socketChannel);
                }else if(selectionKey.isReadable()){//读事件被选取
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //进行读操作
                    int bytesRead = 0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if(read <= 0){
                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);

                        bytesRead += read;
                    }
                    System.out.println("读取" + bytesRead + ", 来自于" + socketChannel);

                    iterator.remove();//事件用完必须要移除掉
                }
            }
        }
    }
}