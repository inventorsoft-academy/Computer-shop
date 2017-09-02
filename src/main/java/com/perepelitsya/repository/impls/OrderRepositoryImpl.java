package com.perepelitsya.repository.impls;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Client;
import com.perepelitsya.model.Order;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import com.perepelitsya.repository.interfaces.OrderRepository;
import com.perepelitsya.util.Converter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final static Logger log = Logger.getLogger(OrderRepositoryImpl.class);

    private final static String saveOrderQuery = "INSERT into orders (product_id, quantity,totalprice, client_id, datebuy) values( ?, ?, ?, ?, ?)";
    private final static String updateOrderQuery = "UPDATE orders SET product_id = ?, quantity=?, client_id =?, datebuy = ?";
    private final static String deleteOrderByIDQuery = "DELETE FROM orders WHERE id = ?";
    private final static String getOrderByIDQuery = "SELECT orders.id , orders.quantity,orders.datebuy, orders.totalprice, orders.client_id ,client.firstname, client.lastname, orders.product_id, product.currency,product.name, product.price " +
            "FROM orders inner join client on orders.client_id = client.id inner JOIN product  on orders.product_id  = product.id WHERE orders.id = ?";
    private final static String getAll = "SELECT orders.id , orders.quantity,orders.datebuy, orders.totalprice, orders.client_id ,client.firstname, client.lastname, orders.product_id, product.currency,product.name, product.price " +
            "FROM orders inner join client on orders.client_id = client.id inner JOIN product  on orders.product_id  = product.id";

    private Connection connection;

    private Converter converter = new Converter();


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
        try {
            Converter converter = new Converter();
            PreparedStatement preparedStatement = connection.prepareStatement(saveOrderQuery);
            preparedStatement.setInt(1, order.getProduct().getId());
            preparedStatement.setInt(2, order.getQuantity());
            preparedStatement.setDouble(3, converter.convertToUAH(order.getProduct().getCurrency(),
                    order.getProduct().getPrice() * order.getQuantity()));
            preparedStatement.setInt(4, order.getClient().getId());
            preparedStatement.setObject(5, order.getDateBuy().toLocalDate());
            preparedStatement.executeUpdate();
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("saved");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());
        }
    }

    @Override
    public void updateOrder(Order order) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateOrderQuery);
            preparedStatement.setInt(1, order.getProduct().getId());
            preparedStatement.setInt(2, order.getQuantity());
            preparedStatement.setInt(3, order.getClient().getId());
            preparedStatement.setObject(4, order.getDateBuy());
            preparedStatement.executeUpdate();
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("saved");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());
        }
    }


    @Override
    public void deleteOrder(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderByIDQuery);
            preparedStatement.setInt(1, id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Order deleted");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrders()  {
        List<Order> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                Order order = new Order();

                Client client = new Client();
                client.setFirstName(resultSet.getString("firstname"));
                client.setLastName(resultSet.getString("lastname"));
                order.setClient(client);

                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCurrency(Currency.valueOf((String) resultSet.getObject("currency")));
                order.setProduct(product);


                order.setDateBuy(resultSet.getTimestamp("datebuy").toLocalDateTime());
                order.setTotalPrice(resultSet.getDouble("totalprice"));
                order.setQuantity((Integer) resultSet.getObject("quantity"));

                orders.add(order);
            }
            log.info("You see all product from db");
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
        return orders;
    }


    public Order getOrderById(int id) {
        Order order = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getOrderByIDQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();

                Client client = new Client();
                client.setFirstName(resultSet.getString("firstname"));
                client.setLastName(resultSet.getString("lastname"));
                order.setClient(client);

                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCurrency(Currency.valueOf((String) resultSet.getObject("currency")));
                order.setProduct(product);


                order.setDateBuy(resultSet.getTimestamp("datebuy").toLocalDateTime());
                order.setQuantity((Integer) resultSet.getObject("quantity"));

            }
            log.info("You see all product from db");
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());
        }
        return order;
    }
}