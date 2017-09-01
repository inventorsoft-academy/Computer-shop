package com.perepelitsya.controller;

import com.perepelitsya.model.Product;
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
    public List<Product> getAll() throws SQLException {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delete(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<Product> getById(@PathVariable int productId) throws SQLException {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }
}