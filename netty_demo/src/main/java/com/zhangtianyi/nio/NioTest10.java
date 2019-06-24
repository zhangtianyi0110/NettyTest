package com.zhangtianyi.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @ClassName: NioTest10
 * @Description: 文件锁(共享锁和排他锁)
 * @author zhangtainyi
 * @date 2019/6/24 16:35
 *
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/java/com/zhangtianyi/nio/resource/NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //起始位置，锁长度，true表示共享锁，flase表示排他锁
        FileLock fileLock = fileChannel.lock(3, 6, true);
        System.out.println("valid:"+fileLock.isValid());
        System.out.println("lock type:"+fileLock.isShared());

        fileLock.release();//释放锁
        fileChannel.close();
    }
}
