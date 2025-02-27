package com.hua.proxy;

import com.hua.common.constants.RpcProxy;
import com.hua.proxy.cglib.CglibProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: ProxyFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 0:09
 */
public class ProxyFactory {


    private static Map<RpcProxy,IProxy> proxyIProxyMap = new HashMap<RpcProxy,IProxy>();

    static{
        proxyIProxyMap.put(RpcProxy.CG_LIB,new CglibProxyFactory());
    }

    public static IProxy get(RpcProxy rpcProxy){
        return proxyIProxyMap.get(rpcProxy);
    }


}
