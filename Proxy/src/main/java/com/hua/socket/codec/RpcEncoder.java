package com.hua.socket.codec;

import com.hua.socket.serialization.RpcSerialization;
import com.hua.socket.serialization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: Elon
 * @title: RpcEncoder
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 20:19
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Object>> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol<Object> objectRpcProtocol, ByteBuf byteBuf) throws Exception {
        // 获取消息头类型
        MsgHeader header =  objectRpcProtocol.getHeader();
        // 写入魔数
        byteBuf.writeShort(header.getMagic());
        // 写入版本号
        byteBuf.writeByte(header.getVersion());
        // 写入消息类型
        byteBuf.writeByte(header.getMsgType());
        // 写入状态
        byteBuf.writeByte(header.getStatus());
        // 写入请求ID
        byteBuf.writeLong(header.getRequestId());
        // 写入序列化方式
        byteBuf.writeInt(header.getSerializationLen());
        final byte[] ser = header.getSerialization();
        final String serialization = new String(ser);
        byteBuf.writeBytes(ser);
        RpcSerialization rpcSerialization = SerializationFactory.get(com.hua.common.constants.RpcSerialization.get(serialization));
        byte[] data = rpcSerialization.serialize(objectRpcProtocol.getBody());
        // 写入数据长度
        byteBuf.writeInt(data.length);
        // 写入数据
        byteBuf.writeBytes(data);

    }
}
