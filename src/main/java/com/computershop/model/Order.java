package com.computershop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {
    private Product product;
    private int quantity;
    private Client client;
    private Date dateBuy;

    public Order(Product product, int quantity, Client client, Date dateBuy) {
        this.product = product;
        this.quantity = quantity;
        this.client = client;
        this.dateBuy = dateBuy;
    }
}

