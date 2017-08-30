package com.perepelitsya.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private long id;
    private int quantity;
    private Product product;
    private LocalDateTime dateBuy;
}

