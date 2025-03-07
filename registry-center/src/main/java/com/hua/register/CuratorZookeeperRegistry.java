package com.hua.register;

import com.alibaba.fastjson.JSON;
import com.hua.common.Cache;
import com.hua.common.URL;
import com.hua.event.*;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.*;

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


    public void register(URL url) throws Exception {
        if(!existNode(ROOT_PATH)){
            client.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT).forPath(ROOT_PATH, "".getBytes());
        }

        final String providerDataPath = getProviderDataPath(url);

        if(existNode(providerDataPath)){
            deleteNode(providerDataPath);
        }

        client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(providerDataPath,JSON.toJSONString(url).getBytes());
    }

    public void unRegister(URL url) throws Exception {
        deleteNode(getProviderDataPath(url));
        super.unRegister(url);
    }


    public List<URL> discoveries(String serviceName, String version) throws Exception {
        List<URL> urls = super.discoveries(serviceName, version);
        if (null == urls || urls.isEmpty()){
            final List<String> strings = client.getChildren().forPath(getProviderPath(serviceName, version));
            if (!strings.isEmpty()) {
                urls = new ArrayList<>();
                for (String string : strings) {
                    final String[] split = string.split(":");
                    urls.add(new URL(split[0],Integer.parseInt(split[1])));
                }
            }
        }
        return urls;
    }

    @Override
    public void subscribe(URL url) throws Exception {
        final String path = getProviderPath(url.getServiceName(),url.getVersion());
        Cache.SUBSCRIBE_SERVICE_LIST.add(url);
        this.watchNodeDataChange(path);
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

    private String getProviderPath(String serviceName,String version) {

        return ROOT_PATH+PROVIDER+"/"+serviceName+"/"+version;
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

    public void watchNodeDataChange(String path) throws Exception {
        PathChildrenCache cache = new PathChildrenCache(client,path,true);

        // 启动PathChildrenCache
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);


        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                final PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                System.out.println("PathChildrenCache event: " + type);

                RpcEventData eventData = null;
                if(type.equals(CHILD_REMOVED)){
                    String path = pathChildrenCacheEvent.getData().getPath();
                    final URL  url = parsePath(path);
                    eventData = new DestroyEventData(url);
                } else if (type.equals(CHILD_UPDATED) || type.equals(CHILD_ADDED)){
                    String path = pathChildrenCacheEvent.getData().getPath();
                    byte[] bytes = client.getData().forPath(path);
                    Object o = JSON.parseObject(bytes,URL.class);
                    eventData = type.equals(CHILD_UPDATED) ? new UpdateRpcEventData(o) : new AddRpcEventData(o);
                }
                RpcListerLoader.sendEvent(eventData);
            }
        });
    }

}
