package com.perepelitsya.repository.impls;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import com.perepelitsya.repository.interfaces.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final static Logger log = Logger.getLogger(ProductRepositoryImpl.class);

    private final static String saveProductQuery = "insert into product ( name, price, currency)  values(?, ?, ?)";
    private final static String updateProductQuery = "UPDATE product SET name=?, price=?, currency=? ";
    private final static String deleteProductByIDQuery = "DELETE FROM product WHERE id = ?";
    private final static String getAllProductsQuery = "SELECT (id, name, price, currency) FROM product";
    private final static String getProductByIDQuery = "select (name, price, currency) from  product where id=?";

    private Connection connection;

    public ProductRepositoryImpl() {
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
            PreparedStatement preparedStatement = connection.prepareStatement(saveProductQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getCurrency().name());
            preparedStatement.executeUpdate();
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product saved");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProductQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setObject(3, product.getCurrency().name());
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product updated");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteProductByIDQuery);
            preparedStatement.setInt(1, id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Product deleted");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {

        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllProductsQuery);
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
            throw new JdbcCustomException(e.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductById(int id) throws JdbcCustomException {
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getProductByIDQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setName((String) resultSet.getObject(1));
                product.setPrice((Double) resultSet.getObject(2));
                product.setCurrency(Currency.valueOf((String) resultSet.getObject(3)));
            }
            log.info("You see product: " + product.getName());
        } catch (SQLException e) {
            throw new JdbcCustomException(e.getMessage());
        }
        return product;
    }
}