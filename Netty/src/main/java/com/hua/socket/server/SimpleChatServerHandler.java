package com.hua.socket.server;

import com.hua.socket.client.SimpleChatClientHandler;
import com.hua.socket.codec.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: Elon
 * @title: SimpleChatServerHandler
 * @projectName: Progressive-RPC-framework
 * @description: 服务端消息处理器
 * @date: 2025/2/26 15:59
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol rpcProtocol) throws Exception {
        System.out.println(rpcProtocol.getBody());
    }
}
