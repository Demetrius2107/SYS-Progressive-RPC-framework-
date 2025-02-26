package com.hua.invoke;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: Elon
 * @title: Invoker
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 19:02
 */
public interface Invoker {

    Object invoke(Invocation invocation) throws InvocationTargetException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
