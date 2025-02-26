package com.hua.socket.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Elon
 * @title: Client
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/23 22:36
 */
public class Client {

    private final Logger logger = LoggerFactory.getLogger(Client.class);

    private final String host;

    private final Integer port;

    private final Bootstrap bootstrap;

    private final EventLoopGroup eventLoopGroup;


    private ChannelFuture channelFuture;


    public Client(String host, Integer port) {
        this.host = host;
        this.port = port;

        bootstrap = new Bootstrap();

        eventLoopGroup = new NioEventLoopGroup(4);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer< SocketChannel >() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new RpcEncoder())
                    }
                })
    }
}
