package com.example.consumerreceivingobject;

import lombok.Data;

@Data
public class Employee {

    private String name;
    private String salary;
    private boolean isRemote;

    public Employee() {
    }

    public Employee(String name, String salary, boolean isRemote) {
        this.name = name;
        this.salary = salary;
        this.isRemote = isRemote;
    }
}
