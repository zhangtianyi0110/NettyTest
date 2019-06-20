package com.zhangtianyi.netty.demo_4.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //空闲状态检测处理器,读，写，读写，时间单位
        pipeline.addLast(new IdleStateHandler(5,6,7, TimeUnit.SECONDS));
        //添加自己的处理方法
        pipeline.addLast(new MyServerHandler());

    }
}
