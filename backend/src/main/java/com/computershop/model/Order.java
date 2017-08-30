package com.computershop.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Data
public class Order {
    private Product product;
    private int quantity;
    private Client client;
    private Date dateBuy;
}

