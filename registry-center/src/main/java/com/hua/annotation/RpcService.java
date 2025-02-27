package com.hua.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Elon
 * @title: RpcService
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 0:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface RpcService {

    String version() default "1.0";
}
