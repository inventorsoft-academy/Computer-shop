package com.perepelitsya.dao.impls;

import com.perepelitsya.dao.interfaces.ProductDao;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public class ProductDaoImpls implements ProductDao {

    private final static Logger log = Logger.getLogger(ProductDaoImpls.class);
    Connection connection;
    PreparedStatement preparedStatement;

    public ProductDaoImpls() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer", "postgres", "root");
            log.info("try to connect to db");
        } catch (Exception e) {
            log.error("problem with connection to db");
        }
    }

    @Override
    public void saveProduct(Product product) {
        try {
            preparedStatement = connection.prepareStatement("insert into product  values(?, ?, ?, ?)");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, String.valueOf(product.getCurrency()));
            preparedStatement.executeUpdate();
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product saved");
            } else {
                log.info("Product don't saved");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot saved new Product");
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE product SET name=?, price=?, currency=? ");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setObject(3, product.getCurrency());
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product updated");
            } else {
                log.info("Product don't updated");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot  updated Product");
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1, (int) id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product deleted");
            } else {
                log.info("Product don't delete");
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
                product.setCurrency((Currency) resultSet.getObject(4));
                products.add(product);
            }
            log.info("You see all product from db");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductById(long id) throws SQLException {
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement("select * from product where id=?");
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId((Integer) resultSet.getObject(1));
                product.setName((String) resultSet.getObject(2));
                product.setPrice((Double) resultSet.getObject(3));
                product.setCurrency((Currency) resultSet.getObject(4));
            }
            log.info("You see product: " + product.getName());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return product;
    }

}
