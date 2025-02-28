package com.hua.common;

import java.util.Objects;

/**
 * @author: Elon
 * @title: ServiceName
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/28 15:01
 */
public class ServiceName {
    private final String name;

    private final String version;

    public ServiceName(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String toString() {
        return "ServiceName{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ServiceName that = (ServiceName) object;
        return Objects.equals(name, that.name) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }

}
