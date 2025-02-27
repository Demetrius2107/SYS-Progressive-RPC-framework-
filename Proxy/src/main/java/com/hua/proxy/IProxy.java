package com.hua.proxy;

/**
 * @author: Elon
 * @title: IProxy
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 0:08
 */
public interface IProxy {

    <T> T getProxy(Class<T> claz) throws InstantiationException, IllegalAccessException;
}
