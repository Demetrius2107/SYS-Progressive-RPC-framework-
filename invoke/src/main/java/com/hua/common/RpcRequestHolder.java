package com.hua.common;

import com.hua.socket.codec.RpcResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Elon
 * @title: RpcRequestHolder
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 16:59
 */
public class RpcRequestHolder {

    // 请求Id
    public final static AtomicLong REQUEST_ID_GEN = new AtomicLong(0);

    // 绑定请求
    public static final Map<Long, RpcFuture<RpcResponse>> REQUEST_MAP = new ConcurrentHashMap();

    public static long getRequestId() {
        if (REQUEST_ID_GEN.longValue() == Long.MAX_VALUE) {
            REQUEST_ID_GEN.set(0);
        }

        return REQUEST_ID_GEN.incrementAndGet();
    }
}
