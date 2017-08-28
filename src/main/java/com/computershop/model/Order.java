package com.computershop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Order {
    private Product product;
    private int quantity;
    private Client client;
    private Date dateBuy;
}

