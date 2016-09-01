package com.hlh.memory;

import java.io.Serializable;

/**
 * User: Hlh(tatian91@163.com)
 * Date: 2016-05-31
 * Time: 15:44
 */
public class Student implements Serializable{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
