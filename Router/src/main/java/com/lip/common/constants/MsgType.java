package com.lip.common.constants;

/**
 * @author: Elon
 * @title: MsgType
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:21
 */
public enum MsgType {

    REQUEST,
    RESPONSE,
    HEARTBEAT;


    public static MsgType findByType(int type) {
        return MsgType.values()[type];
    }
}
