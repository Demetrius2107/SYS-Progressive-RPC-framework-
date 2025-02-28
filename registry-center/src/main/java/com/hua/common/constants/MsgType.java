package com.hua.common.constants;

/**
 * @author: Elon
 * @title: MsgType
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 15:11
 */
public enum MsgType {

    REQUEST,
    RESPONSE,
    HEAERTBEAT;

    public static MsgType findByType(int type) {
        return MsgType.values()[type];
    }

}
