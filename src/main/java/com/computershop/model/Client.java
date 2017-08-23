package com.computershop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private double money;

    public Client(int id, String firstName, String lastName, double money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
    }
}

