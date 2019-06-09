package com.zhangtianyi.netty.demo_2.Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;

/**
* @Description:    定义处理handler
* @Author:         zhangtianyi
* @CreateDate:     2019/6/9 11:05
* @UpdateUser:     zhangtianyi
* @Version:        1.0
*/
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     *
     * @param ctx s上下文对象
     * @param msg 客户端消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+","+msg);
        ctx.channel().writeAndFlush("From Server:"+ DateFormatUtils.format(Calendar.getInstance(),"yyyyMMddHHmmssSSS"));

    }

    /**
     * 捕获异常，一般会关闭连接
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
