package com.zhangtianyi.zerocpoy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
/**
 * @ClassName: OldIOClient
 * @Description: 传统IO拷贝客户端
 * @author zhangtainyi
 * @date 2019/6/28 14:35
 *
 */
public class OldIOClient {
    public static void main(String[] args) throws Exception{


        String filePath = "D:\\BaiduNetdiskDownload\\cn_visio_2010_x64_516562.exe";//测试用文件大约500MB

        try (Socket client = new Socket("localhost", 8899);
             InputStream inputStream = new FileInputStream(filePath);
             DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream()))
        {

            byte[] buffer = new byte[4096];
            long readCount;
            long totalCount = 0;

            long startTime = System.currentTimeMillis();

            while ((readCount = inputStream.read(buffer)) >= 0){
                totalCount += readCount;
                dataOutputStream.write(buffer);
            }
            System.out.println("发送总字节数" + totalCount + ", 耗时：" + (System.currentTimeMillis() - startTime));


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
