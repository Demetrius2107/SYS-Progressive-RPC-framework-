package com.hua.register;

import com.hua.common.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author: Elon
 * @title: CuratorZookeeperRegistry
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:26
 */
public class CuratorZookeeperRegistry extends AbstractZookeeperRegistry{

    // 连接失败等待重试时间
   public static final int BASE_SLEEP_TIME_MS = 1000;

   // 重试次数
   public static final int MAX_RETRIES = 3;

   // 根路径
   public static final String ROOT_PATH = "/rpc";

   private static final String PROVIDER = "/provider";

   private final CuratorFramework client;

    /**
     * 启动zk
     * @throws Exception
     */
    public CuratorZookeeperRegistry(String registerAddr){
        client = CuratorFrameworkFactory.newClient(registerAddr, new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS, MAX_RETRIES));
        client.start();
    }


    public void register(URL url) {

    }


    @Override
    public void subscribe(URL url) throws Exception {

    }

    @Override
    public void unSubscribe(URL url) {

    }


    private String getProviderDataPath(URL url){
        return ROOT_PATH + PROVIDER + "/" + url.getServiceName() + "/" + url.getVersion()
                + "/" + url.getIp() + ":" + url.getPort();

    }
}
