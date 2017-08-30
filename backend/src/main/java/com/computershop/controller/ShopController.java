package com.computershop.controller;

import com.computershop.model.Client;
import com.computershop.repository.ClientBase;
import com.computershop.service.ClientActions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@Controller
@RequestMapping(value = "/clients")
public class ShopController {

    @Autowired
    private ClientBase clientBase;
    private ClientActions getId;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Client> getClients() {
        return clientBase.getClients();
    }

    @GetMapping(value = "/{clientId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {
        return clientBase.findClientById(clientId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/newclient")
    public ResponseEntity<Client> createClientFromJson(@RequestBody Client client) {
        int id = getId.maxId() + 1;
        client.setId(id);
        clientBase.addClient(client);
        return new ResponseEntity(client, HttpStatus.OK);
    }
}

