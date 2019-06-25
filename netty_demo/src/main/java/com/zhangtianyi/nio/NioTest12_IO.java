package com.zhangtianyi.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
/**
 * @ClassName: NioTest12_IO
 * @Description: 传统IO服务端
 * @author zhangtainyi
 * @date 2019/6/25 9:15
 *
 */
public class NioTest12_IO {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new MySocket(socket)).start();
        }
    }
}
class MySocket implements Runnable{
    private Socket socket;

    protected MySocket(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
        ){
            outputStream.write(("welcome,Thread-" + Thread.currentThread().getName()).getBytes(Charset.defaultCharset()));
            outputStream.flush();
            byte[] bs = new byte[1024];
            inputStream.read(bs);
            System.out.println(new String(bs, Charset.defaultCharset()).trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
