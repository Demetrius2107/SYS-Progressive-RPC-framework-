package com.hua.common.constants;

/**
 * @author: Elon
 * @title: MsgType
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/26 23:09
 */
public enum MsgType {

    REQUEST,
    RESPONSE,
    HEARTBEAT;

    public static MsgType findByType(int type){
        return MsgType.values()[type];
    }
}
