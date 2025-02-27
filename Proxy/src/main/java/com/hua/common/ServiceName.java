package com.hua.common;

import java.util.Objects;

/**
 * @author: Elon
 * @title: ServiceName
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/27 14:28
 */
public class ServiceName {

    private final String name;

    public ServiceName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ServiceName{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ServiceName that = (ServiceName) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
