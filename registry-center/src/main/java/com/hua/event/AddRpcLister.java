package com.hua.event;

import com.hua.common.Cache;
import com.hua.common.Host;
import com.hua.common.ServiceName;
import com.hua.common.URL;
import io.netty.channel.ChannelFuture;

import java.util.ArrayList;

public class AddRpcLister implements IRpcLister<AddRpcEventData>{

    @Override
    public void exec(AddRpcEventData addRpcLister) {
        final URL url = (URL) addRpcLister.getData();
        final ServiceName serviceName = new ServiceName(url.getServiceName(), url.getVersion());
        if (!Cache.SERVICE_URLS.containsKey(serviceName)){
            Cache.SERVICE_URLS.put(serviceName,new ArrayList<>());
        }
        Cache.SERVICE_URLS.get(serviceName).add(url);
        final Host ip = new Host(url.getIp(), url.getPort());
        if (!Cache.CHANNEL_FUTURE_MAP.containsKey(ip)) {
            ChannelFuture channelFuture = Cache.BOOT_STRAP.connect(url.getIp(),url.getPort());
            Cache.CHANNEL_FUTURE_MAP.put(ip,channelFuture);
        }
    }
}
