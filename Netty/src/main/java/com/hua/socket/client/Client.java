package com.hua.socket.client;


import com.hua.common.constants.MsgType;
import com.hua.common.constants.ProtocolConstatns;
import com.hua.common.constants.RpcSerialization;
import com.hua.socket.codec.MsgHeader;
import com.hua.socket.codec.RpcDecoder;
import com.hua.socket.codec.RpcEncoder;
import com.hua.socket.codec.RpcProtocol;
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
                                .addLast(new RpcDecoder());
                    }
                });
        channelFuture = bootstrap.connect(host, port).sync();
    }

    public void sendRequest(Object o) {
        channelFuture.channel().writeAndFlush(o);
    }


    public static void main(String[] args) throws InterruptedException {
        final Client nettyClient = new Client("127.0.0.1", 8081);
        final RpcProtocol rpcProtocol = new RpcProtocol();

        // 构建消息头
        MsgHeader header = new MsgHeader();
        long requestId = 123;
        header.setMagic(ProtocolConstatns.MAGIC);
        header.setVersion(ProtocolConstatns.VERSION);
        header.setRequestId(requestId);


        final byte[] serialization = RpcSerialization.JSON.name.getBytes();
        header.setSerializationLen(serialization.length);
        header.setSerialization(serialization);
        header.setMsgType((byte) MsgType.REQUEST.ordinal());
        header.setStatus((byte) 0x1);
        rpcProtocol.setHeader(header);
        rpcProtocol.setBody(new MyObject());
        nettyClient.sendRequest(rpcProtocol);

    }
}

class MyObject {
    String name = "test";

    Integer age = 18;

    Address address = new Address();

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}

class Address {
    String host = "127.0.0.1";

    Integer port = 8080;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "Address{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}