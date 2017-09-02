package com.perepelitsya.repository.impls;

import com.perepelitsya.exception.JdbcCustomException;
import com.perepelitsya.model.Client;
import com.perepelitsya.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriu on 8/31/2017.
 */
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final static Logger log = Logger.getLogger(ClientRepositoryImpl.class);

    private final static String saveClientQuery = "insert into client (firstname, lastname) values( ?, ?)";
    private final static String updateClientQuery = "update client set firstname=?,lastname=? ";
    private final static String deleteClientByIDQuery = "DELETE FROM client WHERE id = ?";
    private final static String getAllClientsQuery = "SELECT * FROM client";
    private final static String getClientByIDQuery = "select * from client where id=?";

    private Connection connection;

    public ClientRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/computer", "postgres", "root");
            log.info("try to connect to db");
        } catch (Exception e) {
            log.error("problem with connection to db");
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveClient(Client client) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveClientQuery);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Client saved");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public void updateClient(Client client) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateClientQuery);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                System.out.println("updated");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public void deleteClient(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteClientByIDQuery);
            preparedStatement.setInt(1, id);
            int ok = preparedStatement.executeUpdate();
            if (ok != 0) {
                log.info("Client deleted");
            }
        } catch (Exception e) {
            throw new JdbcCustomException(e.getMessage());

        }
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllClientsQuery);
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
            throw new JdbcCustomException(e.getMessage());

        }
        return clients;
    }

    @Override
    public Client getClientById(int id) {
        Client client = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getClientByIDQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = new Client();
                client.setId((Integer) resultSet.getObject(1));
                client.setFirstName((String) resultSet.getObject(2));
                client.setLastName((String) resultSet.getObject(3));
            }
            log.info("You see client: " + client.getFirstName());
        } catch (SQLException e) {
            throw new JdbcCustomException(e.getMessage());
        }
        return client;
    }
}
