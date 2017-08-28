package com.computershop.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private double money;
}