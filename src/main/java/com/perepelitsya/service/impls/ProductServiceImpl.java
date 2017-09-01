package com.perepelitsya.service.impls;

import com.perepelitsya.model.Product;
import com.perepelitsya.repository.interfaces.ProductRepository;
import com.perepelitsya.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/30/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void saveProduct(Product product) {
        repository.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        repository.deleteProduct(id);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return repository.getAllProducts();
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        return repository.getProductById(id);
    }
}
