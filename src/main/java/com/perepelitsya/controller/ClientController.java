package com.perepelitsya.controller;

import com.perepelitsya.model.Client;
import com.perepelitsya.service.impls.ClientServiceImpl;
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
@RequestMapping(value = "/clients")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ClientController {

    private ClientServiceImpl service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Client> getAll() throws SQLException {
        return service.getAllClients();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        service.saveClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/{clientId:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> delete(@PathVariable int clientId) {
        service.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{clientId:\\d+}", method = RequestMethod.GET)
    public ResponseEntity<Client> getById(@PathVariable int clientId) {
        return new ResponseEntity<>(service.getClientById(clientId), HttpStatus.OK);
    }
}