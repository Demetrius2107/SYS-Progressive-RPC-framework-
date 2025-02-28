package com.hua.socket.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.io.IOException;

/**
 * @author: Elon
 * @title: JsonSerialization
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 18:30
 */
public class JsonSerialization implements RpcSerialization {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        try {
            // 使用FastJson将对象序列化为Json格式的字符串
            String jsonString = JSON.toJSONString(obj);
            // 将JSON字符串转换为byte数组
            return jsonString.getBytes("UTF-8");
        } catch (JSONException e) {
            throw new IOException("Error serializing object toJSON", e);
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
