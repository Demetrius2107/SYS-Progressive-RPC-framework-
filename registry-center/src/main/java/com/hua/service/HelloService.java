package com.hua.service;

/**
 * @author: Elon
 * @title: HelloService
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 18:15
 */
public class HelloService implements IHelloService {
    @Override
    public Object hello(String text) {
        return "result: " + text;
    }
}
