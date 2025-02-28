package com.hua.register;

import com.hua.common.constants.Register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Elon
 * @title: RegistryFactory
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 19:25
 */
public class RegistryFactory {

    private static Map<Register, RegistryService> registerServiceMap = new HashMap<Register, RegistryService>();

    static {
        registerServiceMap.put(Register.ZOOKEEPER,new CuratorZookeeperRegistry("127.0.0.1:2181"));
    }
    public static RegistryService get(Register register){
        return registerServiceMap.get(register);
    }
}
