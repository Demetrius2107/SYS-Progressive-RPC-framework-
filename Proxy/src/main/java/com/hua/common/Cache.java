package com.hua.common;

import io.netty.channel.ChannelFuture;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: Cache
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 23:08
 */
public class Cache {

    public static Map<ServiceName, URL> services = new HashMap<ServiceName, URL>();

    public static Map<URL, ChannelFuture> channelFutureMap = new HashMap<URL, ChannelFuture>();
}
