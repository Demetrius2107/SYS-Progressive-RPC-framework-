package com.hua.socket.codec;

import com.hua.common.constants.MsgType;
import com.hua.common.constants.ProtocolConstatns;
import com.hua.common.constants.RpcSerialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: Elon
 * @title: RpcDecoder
 * @projectName: Progressive-RPC-framework
 * @description: 解码器
 * @date: 2025/2/26 11:49
 */
public class RpcDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes() < ProtocolConstatns.HEADER_TOTAL_LEN) {
            return;
        }
        // 标记当前读取位置,便于后面回退
        byteBuf.markReaderIndex();

        // 读取魔数字段
        short magic = byteBuf.readShort();

        if (magic != ProtocolConstatns.MAGIC) {
            throw new IllegalArgumentException("magic number is illegal," + magic);
        }

        // 读取版本字段
        byte version = byteBuf.readByte();

        // 读取消息字段
        byte msgType = byteBuf.readByte();

        // 读取响应状态
        byte status = byteBuf.readByte();

        // 读取请求ID
        long requestId = byteBuf.readLong();

        // 获取序列化算法长度
        final int len = byteBuf.readInt();
        if (byteBuf.readableBytes() < len) {
            byteBuf.resetReaderIndex();
            return;
        }

        final byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);
        final String serialization = new String(bytes);

        // 读取消息体的长度
        int dataLength = byteBuf.readInt();

        // 如果可读字节数小于消息体长度，说明还没有接收完整个消息体，回退并返回
        if(byteBuf.readableBytes() < dataLength){
            // 回退标志
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        // 读取数据
        byteBuf.readBytes(data);

        // 处理消息类型
        MsgType msgTypeEnum = MsgType.findByType(msgType);
        if(msgTypeEnum == null){
            return;
        }

        // 构建消息头
        MsgHeader header = new MsgHeader();
        header.setMagic(magic);
        header.setVersion(version);
        header.setStatus(status);
        header.setRequestId(requestId);
        header.setMsgType(msgType);
        header.setSerialization(bytes);
        header.setSerializationLen(len);
        header.setMsgLen(dataLength);

        RpcSerialization rpcSerialization = SerializationFac





    }
}
