package com.lip.socket.serialization;

import com.lip.common.constants.RpcSerialization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: SerializationFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:06
 */
public class SerializationFactory {

    private static Map<RpcSerialization, com.lip.socket.serialization.RpcSerialization> serializationMap
            = new HashMap<RpcSerialization, com.lip.socket.serialization.RpcSerialization>();


    static {
        serializationMap.put(RpcSerialization.JSON, new JsonSerialization());
    }


    public static com.lip.socket.serialization.RpcSerialization get(RpcSerialization rpcSerialization) {
        return serializationMap.get(rpcSerialization);
    }

}
