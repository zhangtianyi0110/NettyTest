package com.zhangtianyi.zerocpoy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @ClassName: OldIOServer
 * @Description: 传统IO拷贝服务端
 * @author zhangtainyi
 * @date 2019/6/28 14:35
 *
 */
public class OldIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArray = new byte[4096];
                long totalCount = 0;
                while (true){
                    int readCount = dataInputStream.read(byteArray, 0, byteArray.length);
                    totalCount += readCount;
                    System.out.println("当次读取" + readCount + "字节...");
                    System.out.println("总共读取" + totalCount + "字节...");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
