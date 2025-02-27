package com.hua.utils;

/**
 * @author: Elon
 * @title: ServiceNameBuilder
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 1:02
 */
public class ServiceNameBuilder {

    public static String builderServiceKey(String serviceName, String serviceVersion) {
        return String.join("$", serviceName, serviceVersion);
    }

    public static String buildServiceNodeInfo(String key, String ip, Integer port) {
        return String.join("#", key, ip, String.valueOf(port));
    }

}
