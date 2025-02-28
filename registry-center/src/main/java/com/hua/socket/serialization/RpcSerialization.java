package com.hua.socket.serialization;

import java.io.IOException;

/**
 * @author: Elon
 * @title: RpcSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 18:30
 */
public interface RpcSerialization {

    <T> byte[] serialize(T obj) throws IOException;

    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;


}
