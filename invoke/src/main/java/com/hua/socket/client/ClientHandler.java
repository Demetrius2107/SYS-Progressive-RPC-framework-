package com.hua.socket.client;

import com.hua.common.RpcFuture;
import com.hua.common.RpcRequestHolder;
import com.hua.socket.codec.RpcProtocol;
import com.hua.socket.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: Elon
 * @title: ClientHandler
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 21:40
 */
public class ClientHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcResponse>> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol<RpcResponse> rpcResponseRpcProtocol) throws Exception {
        long requestId = rpcResponseRpcProtocol.getHeader().getRequestId();
        RpcFuture<RpcResponse> future = RpcRequestHolder.REQUEST_MAP.remove(requestId);
        future.getPromise().setSuccess(rpcResponseRpcProtocol.getBody());
    }


}
