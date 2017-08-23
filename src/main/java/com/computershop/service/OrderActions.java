package com.computershop.service;

import com.computershop.model.Client;
import com.computershop.model.Currency;
import com.computershop.model.Order;
import com.computershop.model.Product;
import com.computershop.repository.Store;
import com.computershop.util.Converter;
import com.computershop.util.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class OrderActions {
    private static final String DELIMITER = "/";

    private Store store;
    private ProductActions productActions;
    private ClientActions clientActions;
    private Converter converter;

    @Autowired
    public OrderActions(Store store, ProductActions productActions, ClientActions clientActions, Converter converter) {
        this.store = store;
        this.productActions = productActions;
        this.clientActions = clientActions;
        this.converter = converter;
    }

    public void makeOrder() {
        clientActions.clientDataToRead();
        productActions.productDataToRead();
        System.out.println("Enter product name to buy: ");
        String productName = Keyboard.input();
        System.out.println("Enter product's quantity: ");
        int quantity = Integer.parseInt(Keyboard.input());
        System.out.println("Enter client's lastname: ");
        String clientName = Keyboard.input();
        Date date = new Date();
        Product product = productActions.findProduct(productName);
        Client client = clientActions.clientFind(clientName);
        double money = client.getMoney();
        double price = converter.convertToUAH(product.getCurrency(), product.getPrice());
        client.setMoney(money - price);
        Order listForBuy = new Order(product, quantity, client, date);
        store.addOrder(listForBuy);
    }

    public void orderDataToFile() {
        File fileName = new File("D:\\orders.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order orders : store.getOrders()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(orders.getProduct().getId())
                        .append(DELIMITER)
                        .append(orders.getProduct().getName())
                        .append(DELIMITER)
                        .append(orders.getProduct().getPrice())
                        .append(DELIMITER)
                        .append(orders.getProduct().getCurrency())
                        .append(DELIMITER)
                        .append(orders.getQuantity())
                        .append(DELIMITER)
                        .append(orders.getClient().getId())
                        .append(DELIMITER)
                        .append(orders.getClient().getFirstName())
                        .append(DELIMITER)
                        .append(orders.getClient().getLastName())
                        .append(DELIMITER)
                        .append(orders.getClient().getMoney())
                        .append(DELIMITER)
                        .append(orders.getDateBuy());
                writer.write(stringBuilder.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void orderDataToRead() {
        File fileName = new File("D:\\orders.txt");
        store.clean();
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] part = reader.readLine().split(DELIMITER);
                String date = part[9];
                Date resultDate = df.parse(date);
                store.addOrder(new Order(new Product(Integer.valueOf(part[0]), part[1], Double.valueOf(part[2]), Currency.valueOf(part[3])),
                        Integer.valueOf(part[4]), new Client(Integer.valueOf(part[5]), part[6], part[7], Double.valueOf(part[8])), resultDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    public void ordersShowList() {
        store.getOrders().forEach(System.out::println);
    }
}
