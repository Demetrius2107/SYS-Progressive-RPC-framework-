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
 * @date: 2025/2/28 18:30
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<Object>> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol<Object> objectRpcProtocol, ByteBuf byteBuf) throws Exception {

        MsgHeader header = objectRpcProtocol.getHeader();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getMsgType());
        byteBuf.writeByte(header.getStatus());
        byteBuf.writeLong(header.getRequestId());
        byteBuf.writeInt(header.getSerializationLen());
        final byte[] ser = header.getSerialization();
        final String serialization = new String(ser);
        byteBuf.writeBytes(ser);

        RpcSerialization rpcSerialization = SerializationFactory.get(com.hua.common.constants.RpcSerialization.get(serialization));

        byte[] data = rpcSerialization.serialize(objectRpcProtocol.getBody());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }
}
