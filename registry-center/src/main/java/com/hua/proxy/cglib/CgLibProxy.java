package com.hua.proxy.cglib;

import com.hua.common.*;
import com.hua.common.constants.MsgType;
import com.hua.common.constants.ProtocolConstants;
import com.hua.common.constants.Register;
import com.hua.common.constants.RpcSerialization;
import com.hua.service.HelloService;
import com.hua.socket.codec.MsgHeader;
import com.hua.socket.codec.RpcProtocol;
import com.hua.socket.codec.RpcRequest;
import com.hua.socket.codec.RpcResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Elon
 * @title: CgLibProxy
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:05
 */
public class CgLibProxy implements MethodInterceptor {

    private final Object object;

    private final String serviceName;

    public CgLibProxy(Object o){
        this.object = o;
        this.serviceName = o.getClass().getName();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
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
        rpcRequest.setMethodCode(method.hashCode());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setServiceVersion("1.0");
        if (null!=objects && objects.length >0){
            rpcRequest.setParameterTypes(objects[0].getClass());
            rpcRequest.setParameter(objects[0]);
        }

        rpcProtocol.setBody(rpcRequest);

        final List<URL> urls = RegistryFactory.get(Register.ZOOKEEPER).discoveries(serviceName, "1.0");
        if (urls.isEmpty()){
            throw new Exception("无服务可用:"+serviceName);
        }
        final URL url = urls.get(0);

        final ChannelFuture channelFuture = Cache.CHANNEL_FUTURE_MAP.get(new Host(url.getIp(),url.getPort()));
        channelFuture.channel().writeAndFlush(rpcProtocol);
        RpcFuture<RpcResponse> future = new RpcFuture(new DefaultPromise(new DefaultEventLoop()), 3000);
        RpcRequestHolder.REQUEST_MAP.put(requestId, future);
        RpcResponse rpcResponse = future.getPromise().sync().get(future.getTimeout(), TimeUnit.MILLISECONDS);
        if (rpcResponse.getException()!=null){
            throw rpcResponse.getException();
        }
        return rpcResponse.getData();
    }
}
