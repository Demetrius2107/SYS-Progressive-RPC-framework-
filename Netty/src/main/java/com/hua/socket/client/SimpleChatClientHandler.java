package com.hua.socket.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: Elon
 * @title: SimpleChatClientHandler
 * @projectName: Progressive-RPC-framework
 * @description: 客户端消息处理器
 * @date: 2025/2/26 14:32
 */
public class SimpleChatClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = Unpooled.copiedBuffer("Hello Server", CharsetUtil.UTF_8);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;
        System.out.println("Client received message : " + inBuffer.toString(CharsetUtil.UTF_8));
    }
}
