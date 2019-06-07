package com.zhangtianyi.netty.demo_1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 服务端初始化器
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    //初始化管道
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();//管道里有许多handler，类似于过滤器，拦截器

        pipeline.addLast("httpServerCodec", new HttpServerCodec());//HttpServerCodec是http编码和解码集合类类似于@springbootApplication

        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());


    }
}
