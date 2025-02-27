package com.hua.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: JdkReflectionInvoker
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 20:59
 */
public class JdkReflectionInvoker implements Invoker {

    private Map<Integer, MethodInvocation> methodInvocationMap = new HashMap<>();


    @Override
    public Object invoke(Invocation invocation) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        final Integer methodCode = invocation.getMethodCode();
        if (!methodInvocationMap.containsKey(methodCode)) {
            final Class<?> aClass = Class.forName(invocation.getClassName());
            final Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            methodInvocationMap.put(methodCode, new MethodInvocation(aClass.newInstance(), method));
        }
        final MethodInvocation methodInvocation = methodInvocationMap.get(methodCode);
        return methodInvocation.invoke(invocation.getParameter());
    }
}
