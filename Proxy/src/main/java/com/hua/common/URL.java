package com.hua.common;


import java.util.Objects;

/**
 * @author: Elon
 * @title: URL
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 14:28
 */
public class URL {

    private String ip;

    private Integer port;

    public URL(String host, Integer port){
        this.ip = host;
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URL url = (URL) obj;
        return Objects.equals(ip,url.ip) && Objects.equals(port,url.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip,port);
    }
}
