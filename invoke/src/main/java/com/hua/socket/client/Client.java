package com.hua.socket.client;

import com.hua.common.RpcFuture;
import com.hua.common.RpcRequestHolder;
import com.hua.common.constants.MsgType;
import com.hua.common.constants.ProtocolConstants;
import com.hua.common.constants.RpcSerialization;
import com.hua.service.HelloService;
import com.hua.socket.codec.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultPromise;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author: Elon
 * @title: Client
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/26 21:40
 */
public class Client {


    private final String host;

    private final Integer port;

    private final Bootstrap bootstrap;
    private final EventLoopGroup eventLoopGroup;

    private ChannelFuture channelFuture;

    public Client(String host, Integer port) throws InterruptedException {
        this.host = host;
        this.port = port;

        bootstrap = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup(4);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new RpcEncoder())
                                .addLast(new RpcDecoder())
                                .addLast(new ClientHandler());
                    }
                });
        channelFuture = bootstrap.connect(host, port).sync();
    }

    public void sendRequest(Object o) {
        channelFuture.channel().writeAndFlush(o);
    }


    public static void main(String[] args) throws Exception {
        final Client nettyClient = new Client("127.0.0.1", 8081);
        final RpcProtocol rpcProtocol = new RpcProtocol();
        // 构建消息头
        MsgHeader header = new MsgHeader();
        long requestId = RpcRequestHolder.getRequestId();
        header.setMagic(ProtocolConstants.MAGIC);
        header.setVersion(ProtocolConstants.VERSION);
        header.setRequestId(requestId);


        final byte[] serialization = RpcSerialization.JSON.name.getBytes();
        header.setSerializationLen(serialization.length);
        header.setSerialization(serialization);
        header.setMsgType((byte) MsgType.REQUEST.ordinal());
        header.setStatus((byte) 0x1);
        rpcProtocol.setHeader(header);
        final RpcRequest rpcRequest = new RpcRequest();

        final Class<HelloService> aClass = HelloService.class;
        rpcRequest.setClassName(aClass.getName());
        final Method method = aClass.getMethod("hello", String.class);
        rpcRequest.setMethodCode(method.hashCode());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setServiceVersion("1.0");
        rpcRequest.setParameterTypes(method.getParameterTypes()[0]);
        rpcRequest.setParameter("xhy~");
        rpcProtocol.setBody(rpcRequest);

        nettyClient.sendRequest(rpcProtocol);

        RpcFuture<RpcResponse> future = new RpcFuture(new DefaultPromise(new DefaultEventLoop()), 3000);

        RpcRequestHolder.REQUEST_MAP.put(requestId, future);
        Object rpcResponse = future.getPromise().sync().get(future.getTimeout(), TimeUnit.MILLISECONDS);
        System.out.println(rpcResponse);

    }
}
