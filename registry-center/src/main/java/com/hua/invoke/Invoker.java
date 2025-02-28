package com.hua.invoke;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: Elon
 * @title: Invoker
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 18:50
 */
public interface Invoker {

    Object invoker(Invocation invocation) throws InvocationTargetException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
