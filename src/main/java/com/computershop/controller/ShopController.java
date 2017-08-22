package com.computershop.controller;

import com.computershop.model.Client;
import com.computershop.repository.ClientBase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/clients")
public class ShopController {

    @Autowired
    private ClientBase clientBase;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Client> getClients() {
        return clientBase.getClients();
    }

    @GetMapping("/{clientId:\\d+}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {
        return clientBase.findClientById(clientId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

