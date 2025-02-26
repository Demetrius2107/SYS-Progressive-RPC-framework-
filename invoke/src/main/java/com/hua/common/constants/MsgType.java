package com.hua.common.constants;

/**
 * @author: Elon
 * @title: MsgType
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 17:15
 */
public enum MsgType {

    REQUEST,
    RESPONSE,
    HEARTBEAT;

    public static MsgType findByType(int type) {
        return MsgType.values()[type];
    }

}
