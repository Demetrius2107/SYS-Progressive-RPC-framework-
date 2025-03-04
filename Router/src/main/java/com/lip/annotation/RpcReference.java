package com.lip.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author: Elon
 * @title: RpcReference
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 15:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface RpcReference {

    String version() default "1.0";

    long time() default 3000;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;


}
