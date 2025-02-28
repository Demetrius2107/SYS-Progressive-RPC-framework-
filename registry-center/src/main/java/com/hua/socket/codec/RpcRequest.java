package com.hua.socket.codec;

import java.io.Serializable;

/**
 * @author: Elon
 * @title: RpcRequest
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 18:30
 */
public class RpcRequest implements Serializable {
    private String serviceVersion;
    private String className;
    private String methodName;

    private Integer methodCode;

    private Object parameter;
    private Class<?> parameterTypes;

    public void setMethodCode(Integer methodCode) {
        this.methodCode = methodCode;
    }

    public Integer getMethodCode() {
        return methodCode;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public void setParameterTypes(Class<?> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Class<?> getParameterTypes() {
        return parameterTypes;
    }
}
