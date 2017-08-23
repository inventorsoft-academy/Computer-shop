package com.computershop;

import com.computershop.service.ClientActions;
import com.computershop.service.OrderActions;
import com.computershop.service.ProductActions;
import com.computershop.util.Converter;
import com.computershop.util.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInterface implements CommandLineRunner {
    private static final String YES = "yes";
    private ClientActions clientActions;
    private ProductActions productActions;
    private OrderActions orderActions;
    private Converter converter;

    @Autowired
    public UserInterface(ClientActions clientActions, ProductActions productActions, OrderActions orderActions,
                         Converter converter) {
        this.clientActions = clientActions;
        this.productActions = productActions;
        this.orderActions = orderActions;
        this.converter = converter;
    }

    @Override
    public void run(String... strings) throws Exception {
        boolean status = true;
        clientActions.clientDataToRead();
        productActions.productDataToRead();
        orderActions.orderDataToRead();
        while (status) {
            System.out.println("Welcome to shop menu \n"
                    + "  1. Make order \n"
                    + "  2. Product options \n"
                    + "  3. Client options \n"
                    + "  4. Set up currency indexes \n"
                    + "  5. Exit");
            switch (Keyboard.input()) {
                case "1":
                    do {
                        orderActions.makeOrder();
                        orderActions.orderDataToFile();
                        clientActions.clientDataToFile();
                    } while (isContinue());
                    break;
                case "2":
                    System.out.println("\nProduct menu \n"
                            + "  1. Add new product \n"
                            + "  2. Update product \n"
                            + "  3. Remove product \n"
                            + "  4. View list of products \n"
                            + "  5. Return to previous menu");
                    switch (Keyboard.input()) {
                        case "1":
                            do {
                                productActions.createProduct();
                                productActions.productDataToFile();
                            } while (isContinue());
                            break;
                        case "2":
                            productActions.updateProduct();
                            productActions.productDataToFile();
                            break;
                        case "3":
                            productActions.removeProduct();
                            productActions.productDataToFile();
                            break;
                        case "4":
                            productActions.productShowList();
                            break;
                        case "5":
                            break;
                    }
                    break;
                case "3":
                    System.out.println("\nClient menu \n"
                            + "  1. Create new client \n"
                            + "  2. Show list of clients \n"
                            + "  3. Show list of bought products \n"
                            + "  4. Return to previous menu");
                    switch (Keyboard.input()) {
                        case "1":
                            do {
                                clientActions.createClient();
                                clientActions.clientDataToFile();
                            } while (isContinue());
                            break;
                        case "2":
                            clientActions.clientListShow();
                            break;
                        case "3":
                            orderActions.ordersShowList();
                            break;
                        case "4":
                            break;
                    }
                    break;
                case "4": {
                    System.out.println("Input new indexes for currency \n EURO");
                    converter.setIndexEur(Double.parseDouble(Keyboard.input()));
                    System.out.println(" USD");
                    converter.setIndexUsd(Double.parseDouble(Keyboard.input()));
                }
                break;
                case "5":
                    status = false;
                    break;
                default:
                    System.out.println("Wrong command \n");
            }
        }
    }

    private boolean isContinue() {
        System.out.println("Continue? yes/no");
        return Keyboard.input().equals(YES);
    }

}