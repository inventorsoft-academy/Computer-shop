package com.perepelitsya.service.interfaces;

import com.perepelitsya.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/30/2017.
 */
public interface ProductService {

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int id) throws SQLException;


}
