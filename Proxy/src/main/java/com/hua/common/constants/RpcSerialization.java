package com.hua.common.constants;

/**
 * @author: Elon
 * @title: RpcSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 23:10
 */
public enum RpcSerialization {

    JSON("json"),
    JDK("jdk");

    public String name;

    RpcSerialization(String type){
        this.name = type;
    }

    public static RpcSerialization get(String type){
        for (RpcSerialization value : values()){
            if(value.name.equals(type)){
                return value;
            }
        }
        return null;
    }
}
