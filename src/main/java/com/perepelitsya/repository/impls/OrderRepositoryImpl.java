package com.perepelitsya.repository.impls;

import com.perepelitsya.repository.interfaces.OrderRepository;
import com.perepelitsya.model.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public class OrderRepositoryImpl implements OrderRepository {

    private final static Logger log = Logger.getLogger(OrderRepositoryImpl.class);

    Connection connection;
    PreparedStatement preparedStatement;

    public OrderRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer", "postgres", "root");
            log.info("try to connect to db");
        } catch (Exception e) {
            log.error("problem with connection to db");
        }
    }


    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM orders WHERE id = ?" );
            preparedStatement.setInt(1, (int) id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Order deleted");
            } else {
                log.info("Order don't delete");
            }
        } catch (Exception e) {
            log.error("Cannot delete  Order");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }


    public Order getOrderById(int id) {
        return null;
    }
}
