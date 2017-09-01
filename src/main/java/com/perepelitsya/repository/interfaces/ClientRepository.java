package com.perepelitsya.repository.interfaces;

import com.perepelitsya.model.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
public interface ClientRepository {
    void saveClient(Client client);

    void updateClient(Client client);

    void deleteClient(int id);

    List<Client> getAllClients() throws SQLException;

   Client getClientById(int id);
}
