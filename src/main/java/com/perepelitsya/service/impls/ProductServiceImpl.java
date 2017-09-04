package com.perepelitsya.service.impls;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import com.perepelitsya.repository.interfaces.ProductRepository;
import com.perepelitsya.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
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
        if (product.validate().isEmpty()) {
            repository.saveProduct(product);
        } else {
            for (Map.Entry<String, String> map : product.validate().entrySet()) {
                System.out.println(map.getValue() + ". False: " + map.getKey());
            }
            throw new JdbcCustomException("Cannot save");
        }
    }

    @Override
    public void updateProduct(Product product, int id) {
        if (product.validate().isEmpty()) {
            repository.updateProduct(product, id);
        } else {
            for (Map.Entry<String, String> map : product.validate().entrySet()) {
                System.out.println(map.getValue() + ". False: " + map.getKey());
            }
            throw new JdbcCustomException("Cannot update");
        }

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
    public Product getProductById(int id) throws JdbcCustomException {
        return repository.getProductById(id);
    }

    public List<Currency> currencyList() {
        return Arrays.asList(Currency.values());
    }
}
