package com.perepelitsya.dao.interfaces;

import com.perepelitsya.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public interface ProductDao {

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(long id);

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(long id) throws SQLException;

}
