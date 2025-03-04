package com.lip.socket.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author: Elon
 * @title: JsonSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/3/4 23:06
 */
public class JsonSerialization implements RpcSerialization {

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        try {
            String jsonString = JSON.toJSONString(obj);
            return jsonString.getBytes("UTF-8");
        } catch (JSONException e) {
            throw new IOException("Error serializing object to JSON", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) throws IOException {

        try {
            String jsonString = new String(data, "UTF-8");
            return JSON.parseObject(jsonString, clz);
        } catch (JSONException e) {
            throw new IOException("Error deserializing JSON to Object", e);
        }
    }
}
