package com.computershop.service;

import com.computershop.model.Client;
import com.computershop.repository.ClientBase;
import com.computershop.util.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Comparator;
import java.util.Objects;

@Service
public class ClientActions {

    private ClientBase clientBase;
    private int clientId = 0;
    private static final String DELIMITER = "/";

    @Autowired
    public ClientActions(ClientBase clientBase) {
        this.clientBase = clientBase;
    }

    // Creating client

    public void createClient() {
        System.out.println("Enter firstname: ");
        String firstName = Keyboard.input();
        System.out.println("Enter lastname: ");
        String lastName = Keyboard.input();
        System.out.println("Enter money: ");
        clientId++;
        double money = Double.parseDouble(Keyboard.input());
        Client client = new Client(clientId, firstName, lastName, money);
        clientBase.addClient(client);
    }

    // Saving client data to file

    public void clientDataToFile() {
        File fileName = new File("D:\\client.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Client client : clientBase.getClients()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(client.getId())
                        .append(DELIMITER)
                        .append(client.getFirstName())
                        .append(DELIMITER)
                        .append(client.getLastName())
                        .append(DELIMITER)
                        .append(client.getMoney());
                writer.write(stringBuilder.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reading client data from file and building database in running application

    public void clientDataToRead() {
        File fileName = new File("D:\\client.txt");
        clientBase.clean();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] part = reader.readLine().split(DELIMITER);
                clientBase.addClient(new Client(Integer.valueOf(part[0]),
                        part[1], part[2], Double.valueOf(part[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientId = maxId();
    }

    // Getting value of maximum id in database for new client

    public int maxId() {
        return clientBase.getClients().stream()
                .map(Client::getId)
                .max(Comparator.naturalOrder())
                .get();
    }

    // Method for show clients list

    public void clientListShow() {
        clientBase.getClients().forEach(System.out::println);
    }

    // Method for search client by name

    public Client clientFind(String name) {
        return clientBase.getClients().stream()
                .filter(client -> Objects.equals(name, client.getLastName()))
                .findFirst()
                .orElse(null);

    }


}
