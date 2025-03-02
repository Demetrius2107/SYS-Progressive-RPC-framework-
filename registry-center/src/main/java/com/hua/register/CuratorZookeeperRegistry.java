package com.hua.register;

import com.hua.common.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * @author: Elon
 * @title: CuratorZookeeperRegistry
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:26
 */
public class CuratorZookeeperRegistry extends AbstractZookeeperRegistry {

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
     *
     * @throws Exception
     */
    public CuratorZookeeperRegistry(String registerAddr) {
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


    private String getProviderDataPath(URL url) {
        return ROOT_PATH + PROVIDER + "/" + url.getServiceName() + "/" + url.getVersion()
                + "/" + url.getIp() + ":" + url.getPort();
    }

    private String getProviderPath(URL url) {
        return ROOT_PATH + PROVIDER + "/" + url.getServiceName() + "/" + url.getVersion();
    }

    private URL parsePath(String path) {
        final String[] split = path.split("/");
        String className = split[3];
        String version = split[4];
        final String[] split1 = split[5].split(":");
        String host = split1[0];
        String port = split1[1];
        final URL url = new URL();

        url.setServiceName(className);
        url.setVersion(version);
        url.setIp(host);
        url.setPort(Integer.parseInt(port));
        return url;
    }

    public boolean deleteNode(String path) {
        try {
            client.delete().forPath(path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existNode(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void watchNodeDataChange(String path) {
        PathChildrenCache cache = new PathChildrenCache(client,path,true);

    }

}
