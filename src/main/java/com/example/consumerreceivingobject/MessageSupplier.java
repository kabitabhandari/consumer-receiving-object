package com.example.consumerreceivingobject;

import lombok.Data;

import java.util.Date;

@Data
public class MessageSupplier {
    private int Id;
    private String name;
    private String description;
    private Date date;

    public MessageSupplier(int id, String name, String description, Date date) {
        Id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

}
