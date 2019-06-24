package com.zhangtianyi.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
/** 
 * @ClassName: NioTest11_Client
 * @Description: Scattering和Gathering
 * @author zhangtainyi
 * @date 2019/6/24 18:14
 *
 */
public class NioTest11_Client {
    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        System.out.println("连接是否已经建立："+socketChannel.isConnected());

        ByteBuffer buffer = ByteBuffer.allocate(9);
        buffer.put("welcomeXX".getBytes(Charset.defaultCharset()));
//        System.out.println("position:"+buffer.position()+",limit"+buffer.limit());
        buffer.flip();
//        System.out.println("position:"+buffer.position()+",limit"+buffer.limit());
        socketChannel.write(buffer);
//        System.out.println("position:"+buffer.position()+",limit"+buffer.limit());
        buffer.flip();
//        System.out.println("position:"+buffer.position()+",limit"+buffer.limit());
        socketChannel.read(buffer);
//        System.out.println("position:"+buffer.position()+",limit"+buffer.limit());
        buffer.flip();
        byte[] bytes = new byte[9];
        buffer.get(bytes);
        System.out.println(new String(bytes, Charset.defaultCharset()));

    }
}
