package com.hua.register;

import com.hua.common.Cache;
import com.hua.common.ServiceName;
import com.hua.common.URL;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Elon
 * @title: AbstractZookeeperRegistry
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:26
 */
public abstract class AbstractZookeeperRegistry implements RegistryService {


    @Override
    public void register(URL url) throws Exception {
        final ServiceName serviceName = new ServiceName(url.getServiceName(),url.getVersion());
        if(!Cache.SERVICE_URLS.containsKey(serviceName)){
            Cache.SERVICE_URLS.put(serviceName,new ArrayList<>());
        }
        Cache.SERVICE_URLS.get(serviceName).add(url);
    }

    @Override
    public void unRegister(URL url) throws Exception {
        final ServiceName serviceName = new ServiceName(url.getServiceName(), url.getVersion());
        if(Cache.SERVICE_URLS.containsKey(serviceName)){
            Cache.SERVICE_URLS.get(serviceName).remove(url);
        }
    }

    @Override
    public List<URL> discoveries(String serviceName, String version) throws Exception {
        final ServiceName key = new ServiceName(serviceName,version);
        List<URL> urls = Cache.SERVICE_URLS.get(key);
        return urls;
    }
}
