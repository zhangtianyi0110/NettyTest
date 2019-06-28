package com.zhangtianyi.zerocpoy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName: NewIOClient
 * @Description: 新IO拷贝客户端
 * @author zhangtainyi
 * @date 2019/6/28 14:38
 *
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception{
        InetSocketAddress address = new InetSocketAddress("localhost", 8899);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(address);
        socketChannel.configureBlocking(true);

        String filePath = "D:\\BaiduNetdiskDownload\\cn_visio_2010_x64_516562.exe";//测试用文件大约500MB
        FileChannel fileChannel = new FileInputStream(filePath).getChannel();

        long startTime = System.currentTimeMillis();
        //从0开始，写入整个长度，写到socketChannel
        /**
         * 会把长度设置为2147483647L也就是大概2GB的大小
         * 所以需要对FileChannel.size()返回值进行判断,当它返回值大于0时始终要执行transferTo方法
         * 因为transferTo单次只能处理2gb左右的长度,同时计算position偏移量
         */
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数" + fileChannel.size() + ", 耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
