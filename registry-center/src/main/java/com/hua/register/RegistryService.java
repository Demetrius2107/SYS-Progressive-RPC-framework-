package com.hua.register;

import com.hua.common.URL;

import java.util.List;

/**
 * @author: Elon
 * @title: RegistryService
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:14
 */
public interface RegistryService {

    void register(URL url) throws Exception;

    void unRegister(URL url) throws Exception;

    List<URL> discoveries(String serviceName, String version) throws Exception;

    void subscribe(URL url) throws Exception;

    void unSubscribe(URL url);

}
