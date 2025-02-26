package com.hua.invoke;

import com.hua.common.constants.RpcInvoker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: InvokerFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 19:24
 */
public class InvokerFactory {

    public static Map<RpcInvoker, Invoker> invokerInvokerMap = new HashMap();


    static {
        invokerInvokerMap.put(RpcInvoker.JDK, new JdkReflectionInvoker());
    }

    public static Invoker get(RpcInvoker rpcInvoker) {
        return invokerInvokerMap.get(rpcInvoker);
    }
}
