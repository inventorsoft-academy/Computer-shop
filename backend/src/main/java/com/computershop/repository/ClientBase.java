package com.computershop.repository;

import com.computershop.model.Client;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ClientBase {

    private List<Client> clientBase;

    @PostConstruct
    public void init() {
        this.clientBase = new ArrayList<>();
    }

    public void addClient(Client client) {
        clientBase.add(client);
    }

    public List<Client> getClients() {
        return clientBase;
    }

    public void clean() {
        clientBase = new ArrayList<>();
    }

    public Optional<Client> findClientById(int clientId) {
        return clientBase.stream()
                .filter(client -> Objects.equals(clientId, client.getId()))
                .findFirst();
    }
}
