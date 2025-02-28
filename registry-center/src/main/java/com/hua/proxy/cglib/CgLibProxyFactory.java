package com.hua.proxy.cglib;

import com.hua.proxy.IProxy;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author: Elon
 * @title: CgLibProxyFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:06
 */
public class CgLibProxyFactory implements IProxy {
    @Override
    public <T> T getProxy(Class<T> claz) throws InstantiationException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claz);
        enhancer.setCallback(new CgLibProxy(claz.newInstance()));
        return (T) enhancer.create();
    }
}
