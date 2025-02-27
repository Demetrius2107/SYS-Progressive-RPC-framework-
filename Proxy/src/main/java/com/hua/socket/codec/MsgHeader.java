package com.hua.socket.codec;

import java.io.Serializable;

/**
 * @author: Elon
 * @title: MsgHeader
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 17:27
 */
public class MsgHeader implements Serializable {

    // 魔数
    private short magic;

    // 协议版本号
    private byte version;

    // 数据类型
    private byte msgType;

    // 状态
    private byte status;

    // 请求 ID
    private long requestId;
    private int serializationLen;
    private byte[] serialization;

    // 数据长度
    private int msgLen;

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getSerializationLen() {
        return serializationLen;
    }

    public void setSerializationLen(int serializationLen) {
        this.serializationLen = serializationLen;
    }

    public byte[] getSerialization() {
        return serialization;
    }

    public void setSerialization(byte[] serialization) {
        this.serialization = serialization;
    }

    public int getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
    }

}
