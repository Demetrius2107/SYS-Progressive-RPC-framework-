package com.lip.common.constants;

/**
 * @author: Elon
 * @title: Register
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:22
 */
public enum Register {

    ZOOKEEPER("zookeeper");

    public String name;

    Register(String type) {
        this.name = type;
    }
}
