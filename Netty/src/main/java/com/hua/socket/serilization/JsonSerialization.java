package com.hua.socket.serilization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: Lipschultz
 * @title: JsonSerialization
 * @projectName: Progressive-RPC-framework
 * @description: json序列化
 * @date: 2025/2/26 13:39
 */
public class JsonSerialization implements RpcSerialization {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        try {
            // 使用fastJson 将对象序列化为JSON格式字符串
            String jsonString = JSON.toJSONString(obj);
            // 将JSON字符串转换为byte数组
            return jsonString.getBytes(StandardCharsets.UTF_8);
        } catch (JSONException e) {
            throw new IOException("Error serializing object to JSON ", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) throws IOException {

        try {
            // 将byte数组转换为JSON字符串
            String jsonString = new String(data, "UTF-8");
            // 使用Fastjson将JSON字符串反序列化为对象
            return JSON.parseObject(jsonString, clz);
        } catch (JSONException e) {
            // 捕获反序列化过程中可能发生的异常
            throw new IOException("Error deserializing JSON to object", e);
        }
    }
}
