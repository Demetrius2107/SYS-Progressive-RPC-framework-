package com.hua.socket.serialization;

import com.hua.common.constants.RpcSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: SerializationFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 17:55
 */
public class SerializationFactory {

    private static Map<RpcSerialization, com.hua.socket.serialization.RpcSerialization> serializationMap = new HashMap<>();

    static {
        serializationMap.put(RpcSerialization.JSON, new JsonSerialization());
    }


    public static com.hua.socket.serialization.RpcSerialization get(RpcSerialization rpcSerialization) {
        return serializationMap.get(rpcSerialization);
    }

}
