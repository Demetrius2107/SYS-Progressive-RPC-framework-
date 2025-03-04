package com.lip.common.constants;

/**
 * @author: Elon
 * @title: LoadBalance
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:19
 */
public enum LoadBalance {

    ROUND("round");

    public String name;

    LoadBalance(String type) {
        this.name = type;
    }
}
