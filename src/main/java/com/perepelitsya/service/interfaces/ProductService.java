package com.perepelitsya.service.interfaces;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/30/2017.
 */
public interface ProductService {

    void saveProduct(Product product);

    void updateProduct(Product product, int id);

    void deleteProduct(int id);

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int id) throws JdbcCustomException;

    List<Currency> currencyList();
}
