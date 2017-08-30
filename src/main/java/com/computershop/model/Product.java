package com.computershop.model;

import lombok.*;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private double price;
    private Currency currency;
}