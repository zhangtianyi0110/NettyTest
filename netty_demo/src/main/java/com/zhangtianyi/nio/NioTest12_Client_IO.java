package com.zhangtianyi.nio;

import java.net.Socket;
import java.nio.charset.Charset;
/**
 * @ClassName: NioTest12_Client_IO
 * @Description: 传统IO客户端
 * @author zhangtainyi
 * @date 2019/6/25 9:18
 *
 */
public class NioTest12_Client_IO {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8899)) {
            byte[] bs = new byte[1024];
            socket.getInputStream().read(bs);
            System.out.println(new String(bs, Charset.defaultCharset()).trim());
            socket.getOutputStream().write("hello,Server".getBytes(Charset.defaultCharset()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
