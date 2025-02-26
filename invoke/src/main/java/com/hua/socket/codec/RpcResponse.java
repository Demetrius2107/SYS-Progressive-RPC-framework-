package com.hua.socket.codec;

import java.io.Serializable;

/**
 * @author: Elon
 * @title: RpcResponse
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/26 17:03
 */
public class RpcResponse implements Serializable {

    private Object data;
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public Object getData() {
        return data;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
