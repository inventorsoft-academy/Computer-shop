package com.perepelitsya.repository.impls;

import com.perepelitsya.repository.interfaces.ProductRepository;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final static Logger log = Logger.getLogger(ProductRepositoryImpl.class);
    private Connection connection;

    public ProductRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer", "postgres", "root");
            log.info("try to connect to db");
        } catch (Exception e) {
            log.error("problem with connection to db");
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product ( name, price, currency)  values(?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getCurrency().name());
            preparedStatement.executeUpdate();
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product saved");
            }
        } catch (Exception e) {
            //todo need to handle error
            log.error(e.getMessage());
            log.error("Cannot saved new Product");
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name=?, price=?, currency=? ");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setObject(3, product.getCurrency().name());
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product updated");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot  updated Product");
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product deleted");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot delete  Product");
        }

    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
        List<Product> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId((Integer) resultSet.getObject(1));
                product.setName((String) resultSet.getObject(2));
                product.setPrice((Double) resultSet.getObject(3));
                product.setCurrency(Currency.valueOf((String) resultSet.getObject(4)));
                products.add(product);
            }
            log.info("You see all product from db");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id=?");
            preparedStatement.setInt(1,  id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId((Integer) resultSet.getObject(1));
                product.setName((String) resultSet.getObject(2));
                product.setPrice((Double) resultSet.getObject(3));
                product.setCurrency(Currency.valueOf((String) resultSet.getObject(4)));
            }
            log.info("You see product: " + product.getName());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return product;
    }

//    @PreDestroy
//    private void close(){
//        if(connection!=null)
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                //todo handle exception
//            }
//    }
}