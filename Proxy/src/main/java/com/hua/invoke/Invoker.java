package com.hua.invoke;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: Elon
 * @title: Invoker
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 20:54
 */
public interface Invoker {

    Object invoke(Invocation invocation) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException;
}
