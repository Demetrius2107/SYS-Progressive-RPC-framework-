package com.hua.socket.codec;

import com.hua.socket.serilization.RpcSerialization;
import com.hua.socket.serilization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @author: RpcEncoder
 * @title: RpcEncoder
 * @projectName: Progressive-RPC-framework
 * @description: RPC编码器
 * @date: 2025/2/26 14:15
 */
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol<RpcProtocol<Object>>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol<RpcProtocol<Object>> rpcProtocolRpcProtocol, ByteBuf byteBuf) throws Exception {

        // 获取消息头类型
        MsgHeader msgHeader = rpcProtocolRpcProtocol.getHeader();

        // 写入魔数 (安全校验、参考JAVA中的 CAFEBASE)
        byteBuf.writeShort(msgHeader.getMagic());

        // 写入版本号
        byteBuf.writeByte(msgHeader.getVersion());

        // 写入消息类型
        byteBuf.writeByte(msgHeader.getMsgType());

        // 写入状态
        byteBuf.writeByte(msgHeader.getStatus());

        // 写入请求ID (请求ID 可以用于记录异步回调标识)
        byteBuf.writeLong(msgHeader.getRequestId());

        // 写入序列化方式
        byteBuf.writeInt(msgHeader.getSerializationLen());
        final byte[] ser = msgHeader.getSerialization();
        final String serialization = new String(ser);
        byteBuf.writeBytes(ser);
        RpcSerialization rpcSerialization = SerializationFactory.get(com.hua.common.constants.RpcSerialization.get(serialization));
        byte[] data = rpcSerialization.serialize(rpcProtocolRpcProtocol.getBody());
        // 写入数据长度(接收方根据长度读取数据内容)
        byteBuf.writeInt(data.length);
        // 写入数据
        byteBuf.writeBytes(data);
    }
}
