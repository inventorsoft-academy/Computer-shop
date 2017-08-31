package com.perepelitsya.dao.interfaces;

import com.perepelitsya.model.Order;

import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public interface OrderDao {

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(long id);

    List<Order> getAllOrders();

    Order getOrderById(long id);

}
