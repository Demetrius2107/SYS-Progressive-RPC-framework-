package com.lip.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Elon
 * @title: RpcService
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 14:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface RpcService {

    /**
     * 指定实现方，默认接口中第一个
     *
     * @return
     */
    Class<?> serviceInterface() default  void.class;

    /**
     * 版本
     *
     * @return
     */
    String version() default "1.0";

}
