package com.zhangtianyi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: NioServer
 * @Description: Nio服务端
 * @author zhangtainyi
 * @date 2019/6/26 10:23
 *
 */
public class NioServer {
    private static Map<String, SocketChannel> clientMap = new HashMap<>();//维护所有客户端对信息

    public static void main(String[] args) throws Exception {
        //固定模板代码
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));
        //服务端注册到选择器
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //服务端监听
        while (true){
            try {
                selector.select();//这种调用在没有通道就绪时将无限阻塞,有感兴趣事件发生时通过

                Set<SelectionKey> selectionKeys = selector.selectedKeys();//获取selectionKeys集合
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if(selectionKey.isAcceptable()){//判断是否有客户端连接
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();//获得server对象
                            client = server.accept();//获取对应的客户端对象client
                            client.configureBlocking(false);//配置成非阻塞
                            client.register(selector, SelectionKey.OP_READ);//注册到selector，感兴趣事件为读
//                            client.register(selector, SelectionKey.OP_WRITE);

                            String key = "[" + client.getRemoteAddress() + "]";//设置地址为键
                            System.out.println(key + "已连接");
                            clientMap.put(key, client);//放入map
                        }else if(selectionKey.isReadable()){//读事件触发
                            client = (SocketChannel)selectionKey.channel();//获取client对象
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            int count = client.read(byteBuffer);//读取
                            if(count > 0){
                                byteBuffer.flip();

                                String receiveMessage = String.valueOf(Charset.defaultCharset().decode(byteBuffer).array());//获取返回消息
                                System.out.println(client + ":" + receiveMessage);

                                String senderKey = "[" + client.getRemoteAddress() + "]";//组织返回消息的key

                                clientMap.entrySet().forEach(entry -> {
                                    ByteBuffer wirteBuffer = ByteBuffer.allocate(1024);
                                    String sendMessage = "来自" + senderKey + "的消息：" + receiveMessage;//组织返回消息
                                    wirteBuffer.put(sendMessage.getBytes(Charset.defaultCharset()));
                                    wirteBuffer.flip();

                                    SocketChannel curClient = entry.getValue();//获取当前被选中的client

                                    try {
                                        curClient.write(wirteBuffer);//写入
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                selectionKeys.clear();//当次循环完，清空集合

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
