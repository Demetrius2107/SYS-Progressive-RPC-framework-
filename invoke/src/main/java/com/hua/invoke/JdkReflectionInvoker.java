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
 * @date: 2025/2/26 19:10
 */
public class JdkReflectionInvoker implements Invoker {

    private Map<Integer, MethodInvocation> methodCahce = new HashMap<Integer, MethodInvocation>();

    @Override
    public Object invoke(Invocation invocation) throws InvocationTargetException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Integer methodCode = invocation.getMethodCode();
        if (!methodCahce.containsKey(methodCode)) {
            final Class<?> aClass = Class.forName(invocation.getClassName());
            final Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            methodCahce.put(methodCode, new MethodInvocation(aClass.newInstance(), method));
        }
        final MethodInvocation methodInvocation = methodCahce.get(methodCode);
        return methodInvocation.invoke(invocation.getParameter());
    }
}
