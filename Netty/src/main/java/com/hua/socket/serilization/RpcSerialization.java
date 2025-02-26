package com.hua.socket.serilization;

import java.io.IOException;

/**
 * @author: Elon
 * @title: RpcSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 13:39
 */
public interface RpcSerialization {

    <T> byte[] serialize(T obj)  throws IOException;

    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;
}
