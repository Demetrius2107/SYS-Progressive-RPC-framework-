package com.hua.service;

/**
 * @author: Elon
 * @title: HelloService
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 17:26
 */
public class HelloService implements IHelloService {
    @Override
    public Object hello(String text) {
        return "result : " + text;
    }
}
