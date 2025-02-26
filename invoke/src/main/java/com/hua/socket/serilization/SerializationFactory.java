package com.hua.socket.serilization;

import java.util.HashMap;
import java.util.Map;

public class SerializationFactory {

    private static Map<com.hua.common.constants.RpcSerialization, RpcSerialization> serializationMap
            = new HashMap<com.hua.common.constants.RpcSerialization, RpcSerialization>();

    static {
        serializationMap.put(com.hua.common.constants.RpcSerialization.JSON,new JsonSerialization());
    }

    public static RpcSerialization get(com.hua.common.constants.RpcSerialization serialization){
        return serializationMap.get(serialization);
    }
}
