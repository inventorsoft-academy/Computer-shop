package com.perepelitsya.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int id;
    private int quantity;


    private double totalPrice;

    private Product product;
    private Client client;
    private LocalDateTime dateBuy;
}