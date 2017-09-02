package com.perepelitsya.service.impls;

import com.perepelitsya.model.Order;
import com.perepelitsya.repository.interfaces.OrderRepository;
import com.perepelitsya.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andriu on 8/30/2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;


    @Override
    public void saveOrder(Order order) {
        repository.saveOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        repository.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        repository.deleteOrder(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.getAllOrders();
    }

    @Override
    public Order getOrderById(int id) {
        return repository.getOrderById(id);
    }
}
