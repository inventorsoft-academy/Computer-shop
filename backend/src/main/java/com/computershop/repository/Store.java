package com.computershop.repository;

import com.computershop.model.Order;
import com.computershop.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class Store {
    private Map<Product, Integer> products;
    private List<Order> orders;

    public Store() {
        this.products = new HashMap<>();
        this.orders = new ArrayList<>();
    }

    public void addProduct(Product product, Integer count) {
        products.put(product, count);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void clean() {
        orders = new ArrayList<>();
    }
}