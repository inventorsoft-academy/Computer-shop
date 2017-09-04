package com.perepelitsya.controller;

import com.perepelitsya.model.Order;
import com.perepelitsya.service.impls.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class
OrderController {

    private OrderServiceImpl service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Order> getAll() {
        return service.getAllOrders();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        service.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        service.updateOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@PathVariable int orderId) {
        service.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<Order> getById(@PathVariable int orderId) {
        return new ResponseEntity<>(service.getOrderById(orderId), HttpStatus.OK);
    }
}