package com.perepelitsya.repository.interfaces;

import com.perepelitsya.model.Order;

import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public interface OrderRepository {

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int id);

    List<Order> getAllOrders();

    Order getOrderById(int id);

}
