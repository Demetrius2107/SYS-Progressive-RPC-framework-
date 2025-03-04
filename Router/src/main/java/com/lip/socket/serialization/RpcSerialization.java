package com.lip.socket.serialization;

import java.io.IOException;

/**
 * @author: Elon
 * @title: RpcSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:06
 */
public interface RpcSerialization {

    <T> byte[] serialize(T obj) throws IOException;

    <T> T deserialize(byte[] data , Class<T> clz) throws IOException;

}
