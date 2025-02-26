package com.hua.socket.server;

import com.hua.common.constants.MsgType;
import com.hua.common.constants.RpcInvoker;
import com.hua.invoke.Invocation;
import com.hua.invoke.Invoker;
import com.hua.invoke.InvokerFactory;
import com.hua.socket.codec.MsgHeader;
import com.hua.socket.codec.RpcProtocol;
import com.hua.socket.codec.RpcRequest;
import com.hua.socket.codec.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: Elon
 * @title: ServerHandler
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 21:12
 */
public class ServerHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol<RpcRequest> rpcRequestRpcProtocol) throws Exception {

        final RpcRequest rpcRequest = rpcRequestRpcProtocol.getBody();
        final RpcResponse response = new RpcResponse();
        final RpcProtocol<RpcResponse> rpcResponseProtocol = new RpcProtocol<RpcResponse>();
        final MsgHeader header = rpcRequestRpcProtocol.getHeader();

        header.setMsgType((byte) MsgType.RESPONSE.ordinal());
        rpcResponseProtocol.setHeader(header);
        final Invoker invoker = InvokerFactory.get(RpcInvoker.JDK);

        try {
            final Object data = invoker.invoke(new Invocation(rpcRequest));
            response.setData(data);
        } catch (Exception e) {
            response.setException(e);
        }
        rpcResponseProtocol.setBody(response);
        channelHandlerContext.writeAndFlush(rpcResponseProtocol);
    }
}
