package com.hua;

import java.io.Serializable;

/**
 * @author: Elon
 * @title: MyObject
 * @projectName: Progressive-RPC-framework
 * @description:
 * @date: 2025/2/23 18:08
 */
public class MyObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldA;

    private String fieldB;


    public MyObject(String fieldA,String fieldB){
        this.fieldA = fieldA;
        this.fieldB = fieldB;
    }


    @Override
    public String toString() {
        return "MyObject{" +
                "fieldA='" + fieldA + '\'' +
                ", fieldB='" + fieldB + '\'' +
                '}';
    }
}
