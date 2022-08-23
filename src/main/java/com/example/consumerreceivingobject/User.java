package com.example.consumerreceivingobject;

import lombok.Data;

@Data
public class User {
    private String name;
    private String location;
    private Long householdIncome;

    public User() {

    }

    public User(String name, String dept, Long householdIncome) {
        this.name = name;
        this.location = dept;
        this.householdIncome = householdIncome;
    }
}
