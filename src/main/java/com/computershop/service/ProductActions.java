package com.computershop.service;

import com.computershop.model.Currency;
import com.computershop.model.Product;
import com.computershop.repository.Store;
import com.computershop.util.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductActions {
    private static final String DELIMITER = "/";

    private Store store;
    private int productId = 1;

    @Autowired
    public ProductActions(Store store) {
        this.store = store;
    }

    public void createProduct() {
        System.out.println("Enter productname: ");
        String name = Keyboard.input();
        System.out.println("Enter price: ");
        double price = Double.parseDouble(Keyboard.input());
        System.out.println("Enter currency (type EUR, USD or UAH): ");
        String currency = Keyboard.input();
        System.out.println("Enter quantity: ");
        int quantity = Integer.parseInt(Keyboard.input());
        Product product = new Product(productId, name, price, Currency.valueOf(currency));
        store.addProduct(product, quantity);
        productId++;
    }

    public void productDataToFile() {
        File fileName = new File("D:\\product.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<Product, Integer> pair : store.getProducts().entrySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                Product product = pair.getKey();
                Integer quantity = pair.getValue();
                stringBuilder.append(product.getId())
                        .append(DELIMITER)
                        .append(product.getName())
                        .append(DELIMITER)
                        .append(product.getPrice())
                        .append(DELIMITER)
                        .append(product.getCurrency().name())
                        .append(DELIMITER)
                        .append(quantity);
                writer.write(stringBuilder.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void productDataToRead() {
        File fileName = new File("D:\\product.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] part = reader.readLine().split(DELIMITER);
                store.addProduct(new Product(Integer.valueOf(part[0]),
                                part[1], Double.valueOf(part[2]),
                                Currency.valueOf(part[3])),
                        Integer.valueOf(part[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product findProduct(String name) {
        return store.getProducts().entrySet().stream()
                .map(Map.Entry::getKey)
                .filter(product -> Objects.equals(name, product.getName()))
                .findFirst()
                .orElse(null);
    }

    public void removeProduct() {
        System.out.println("Enter product name to remove ");
        String name = Keyboard.input();
        store.getProducts().remove(findProduct(name));
    }

    public void productShowList() {
        store.getProducts().entrySet().stream()
                .map(pair -> pair.getKey() + " " + pair.getValue())
                .forEach(System.out::println);
    }

    public void updateProduct() {
        System.out.println("Enter product name to update");
        String name = Keyboard.input();
        System.out.println("Enter new price");
        double newPrice = Double.parseDouble(Keyboard.input());
        System.out.println("Enter new quantity");
        int quantity = Integer.parseInt(Keyboard.input());
        Product product = findProduct(name);
        store.getProducts().remove(product);
        product.setPrice(newPrice);
        store.getProducts().put(product, quantity);
    }
}
