package com.hua.common.constants;

/**
 * @author: Elon
 * @title: MsgType
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/26 11:51
 */
public enum MsgType {

    REQUEST,
    RESPONSE,
    HEARTBEAT;

    public static MsgType findByType(int type){
        return MsgType.values()[type];
    }
}
