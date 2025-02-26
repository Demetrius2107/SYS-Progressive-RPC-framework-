package com.hua.common.constants;

/**
 * @author: Elon
 * @title: RpcInvoker
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 17:20
 */
public enum RpcInvoker {

    JDK("jdk");

    public String name;

    RpcInvoker(String type) {
        this.name = type;
    }
}
