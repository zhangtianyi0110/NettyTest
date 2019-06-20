package com.zhangtianyi.netty.demo_5.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //处理http
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());//以块的方式写
        /**
         *  对http消息聚合，聚合成一个完整的http请求/响应，长度超过指定长度（8192），handleOversizedMessage被调用
         */
        pipeline.addLast(new HttpObjectAggregator(8192));

        /**
         *   处理websocket,处理websocket所有繁重的工作，例如握手，心跳，文本，二进制会传给下一个handler,/ws是url
         *   数据以frame(帧)形式
         */

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));


        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
