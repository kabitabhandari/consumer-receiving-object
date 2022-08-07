package com.example.consumerreceivingobject;

import lombok.Data;

@Data
public class User {

    private String name;
    private String dept;

    public User() {
    }

    public User(String name, String dept) {

        this.name = name;
        this.dept = dept;
    }
}
