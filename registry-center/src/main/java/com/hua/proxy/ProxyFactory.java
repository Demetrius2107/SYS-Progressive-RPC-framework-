package com.hua.proxy;

import com.hua.common.constants.RpcProxy;
import com.hua.proxy.cglib.CgLibProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: ProxyFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:05
 */
public class ProxyFactory {

    private static Map<RpcProxy,IProxy> proxyIProxyMap = new HashMap<>();

    static{
        proxyIProxyMap.put(RpcProxy.CG_LIB,new CgLibProxyFactory());
    }

    public static IProxy get(RpcProxy rpcProxy){
        return proxyIProxyMap.get(rpcProxy);
    }

}
