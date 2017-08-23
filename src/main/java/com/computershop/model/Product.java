package com.computershop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Product {
    private int id;
    private String name;
    private double price;
    private Currency currency;

    public Product(int id, String name, double price, Currency currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }
}
