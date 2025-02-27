package com.hua.proxy.cglib;

import com.hua.proxy.IProxy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author: Elon
 * @title: CglibProxyFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 0:09
 */
public class CglibProxyFactory<T> implements IProxy {

    @Override
    public <T> T getProxy(Class<T> claz) throws InstantiationException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claz);
        enhancer.setCallback((Callback) new CgLibProxy(claz.newInstance()));
        return (T) enhancer.create();
    }
}
