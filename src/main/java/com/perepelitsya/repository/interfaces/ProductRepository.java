package com.perepelitsya.repository.interfaces;

import com.perepelitsya.model.Product;
import com.perepelitsya.exception.JdbcCustomException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public interface ProductRepository {

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int id) throws  JdbcCustomException;

}
