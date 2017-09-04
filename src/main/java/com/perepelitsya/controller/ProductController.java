package com.perepelitsya.controller;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Product;
import com.perepelitsya.model.enums.Currency;
import com.perepelitsya.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProductController {

    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Product>> getAll() throws SQLException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ExceptionHandler(JdbcCustomException.class)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId:\\d+}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int productId) {
        productService.updateProduct(product,productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delete(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId:\\d+}", method = RequestMethod.GET)
    @ExceptionHandler(JdbcCustomException.class)
    public ResponseEntity<Product> getById(@PathVariable int productId) throws JdbcCustomException {
        if (productService.getProductById(productId) != null) {
            return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/currency", method = RequestMethod.GET)
    public ResponseEntity<List<Currency>> getCurrency() {
        if (productService.currencyList() != null) {
            return new ResponseEntity<>(productService.currencyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}