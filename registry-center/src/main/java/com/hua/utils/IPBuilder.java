package com.hua.utils;

/**
 * @author: Elon
 * @title: IPBuilder
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 0:59
 */
public class IPBuilder {

    private static final String symbol = ":";

    public static String buildIp(String host, Integer port) {
        return host + symbol + port;
    }
}
