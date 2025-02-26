package com.hua.socket.serilization;

import com.hua.common.constants.RpcSerialization;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: SerializationFactory
 * @projectName: Progressive-RPC-framework
 * @description: 序列化工厂方法
 * @date: 2025/2/26 13:39
 */
public class SerializationFactory {


    private static Map<RpcSerialization, com.hua.socket.serilization.RpcSerialization> serializationMap
            = new HashMap<RpcSerialization, com.hua.socket.serilization.RpcSerialization>();

    static {
        serializationMap.put(RpcSerialization.JSON, new JsonSerialization());
    }

    public static com.hua.socket.serilization.RpcSerialization get(RpcSerialization serialization) {
        return serializationMap.get(serialization);
    }


}
