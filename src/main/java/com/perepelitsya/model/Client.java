package com.perepelitsya.model;

import com.perepelitsya.util.CustomValidator;
import lombok.*;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Client implements CustomValidator {
    private int id;
    private String firstName;
    private String lastName;

    @Override
    public HashMap<String, String> validate() {
        HashMap<String, String> valid = new HashMap<>();
        if (firstName == null || firstName.length() < 4 || firstName.length() > 40) {
            valid.put(firstName, "Incorrect value firstname\nFirstName cannot be null. FirstName must be more than 4 and less than 20");
        }
        if (lastName == null || lastName.length() < 4 || lastName.length() > 40) {
            valid.put(lastName, "Incorrect value lastname\nLastName cannot be null. LastName must be more than 4 and less than 20");
        }
        return valid;
    }
}
