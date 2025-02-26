package com.hua.socket.serilization;

import com.hua.common.constants.RpcSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: SerializationFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 17:51
 */
public class SerializationFactory {

    private static Map<RpcSerialization, com.hua.socket.serilization.RpcSerialization>
    serializationMap = new HashMap<RpcSerialization, com.hua.socket.serilization.RpcSerialization>();

    static {
        serializationMap.put(RpcSerialization.JSON,new JsonSerialization());
    }

    public static com.hua.socket.serilization.RpcSerialization get(RpcSerialization serialization){
        return serializationMap.get(serialization);
    }
}
