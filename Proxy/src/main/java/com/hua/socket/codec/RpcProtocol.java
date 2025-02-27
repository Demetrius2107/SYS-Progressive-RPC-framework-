package com.hua.socket.codec;

import java.io.Serializable;

/**
 * @author: Elon
 * @title: RpcProtocol
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 17:32
 */
public class RpcProtocol<T> implements Serializable {

    private MsgHeader header;
    private T body;

    public MsgHeader getHeader() {
        return header;
    }

    public void setHeader(MsgHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
