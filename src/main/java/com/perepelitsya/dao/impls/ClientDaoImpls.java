package com.perepelitsya.dao.impls;

import com.perepelitsya.dao.interfaces.ClientDao;
import com.perepelitsya.model.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@Component
public class ClientDaoImpls implements ClientDao {

    private final static Logger log = Logger.getLogger(ClientDaoImpls.class);

    Connection connection;
    PreparedStatement preparedStatement;

    public ClientDaoImpls() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer", "postgres", "root");
            log.info("try to connect to db");
        } catch (Exception e) {
            log.error("problem with connection to db");
        }
    }

    @Override
    public void saveClient(Client client) {
        try {
            preparedStatement = connection.prepareStatement("insert into client values(?, ?, ?)");
            preparedStatement.setInt(1, (int) client.getId());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Client saved");
            } else {
                log.info("Client don't saved");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot saved new Client");
        }
    }

    @Override
    public void updateClient(Client client) {
        try {
            preparedStatement = connection.prepareStatement("update client set firstname=?,lastname=? ");
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                System.out.println("updated");
            } else {
                System.out.println("not updated");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot updated our client");
        }
    }

    @Override
    public void deleteClient(long id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            preparedStatement.setInt(1, (int) id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Client deleted");
            } else {
                log.info("Client don't delete");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Cannot delete  Client");
        }
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
        List<Client> clients = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Client client = new Client();
                client.setId((Integer) resultSet.getObject(1));
                client.setFirstName((String) resultSet.getObject(2));
                client.setLastName((String) resultSet.getObject(3));
                clients.add(client);
            }
            log.info("You see all clients from db");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return clients;
    }

    public Client getClientById(long id) {
        Client client = null;
        try {
            preparedStatement = connection.prepareStatement("select * from client where id=?");
            preparedStatement.setInt(1, (int) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = new Client();
                client.setId((Integer) resultSet.getObject(1));
                client.setFirstName((String) resultSet.getObject(2));
                client.setLastName((String) resultSet.getObject(3));
            }
            log.info("You see client: " + client.getFirstName());
        } catch (SQLException e) {
            log.error(e.getMessage());

        }
        return client;
    }
}

