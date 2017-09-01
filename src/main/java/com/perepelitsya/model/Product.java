package com.perepelitsya.model;

import com.perepelitsya.model.enums.Currency;
import com.perepelitsya.util.CustomValidator;
import lombok.*;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Product implements CustomValidator {
    private int id;
    private String name;
    private double price;
    private Currency currency;

    @Override
    public HashMap<String, String> validate() {
        HashMap<String, String> valid = new HashMap<>();
        if (name == null || name.length() < 4 || name.length() > 50) {
            valid.put(name, "Incorrect value name\nName cannot be null. Name must be more than 4 and less than 20");
        }
        if (price == 0 || price <= 0 || price >= 10000) {
            valid.put(String.valueOf(price), "Incorrect value price\nPrice cannot be null. Price must be more than 0 and less than 10000");
        }
        return valid;
    }

}