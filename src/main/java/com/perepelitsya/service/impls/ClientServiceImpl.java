package com.perepelitsya.service.impls;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Client;
import com.perepelitsya.repository.impls.ClientRepositoryImpl;
import com.perepelitsya.repository.interfaces.ClientRepository;
import com.perepelitsya.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Andriu on 8/30/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepository;


    @Override
    public void saveClient(Client client) {
        if(client.validate().isEmpty()) {
            clientRepository.saveClient(client);
        }
        else{
            for (Map.Entry<String, String> map : client.validate().entrySet()) {
                System.out.println(map.getValue() + ". False: " + map.getKey());
            }
            throw  new JdbcCustomException("Cannot save");
        }
    }

    @Override
    public void updateClient(Client client, int id) {
        if(client.validate().isEmpty()) {
            clientRepository.updateClient(client, id);
        }
        else{
            for (Map.Entry<String, String> map : client.validate().entrySet()) {
                System.out.println(map.getValue() + ". False: " + map.getKey());
            }
            throw  new JdbcCustomException("Cannot update");
        }

    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteClient(id);
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        return clientRepository.getAllClients();
    }

    @Override
    public Client getClientById(int id) {
        return clientRepository.getClientById(id);
    }
}