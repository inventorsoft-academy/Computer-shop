package com.computershop.util;

import com.computershop.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    private double INDEX_EUR = 30;
    private double INDEX_USD = 26;

    public double convertToUAH(Currency currency, double amount) {
        double result = 0;
        switch (currency) {
            case EUR:
                result = amount * INDEX_EUR;
                break;
            case USD:
                result = amount * INDEX_USD;
                break;
            case UAH:
                result = amount;
                break;
        }
        return result;
    }

    public double setIndexEur(double index) {
        INDEX_EUR = index;
        return INDEX_EUR;
    }

    public double setIndexUsd(double index) {
        INDEX_USD = index;
        return INDEX_USD;
    }

}
