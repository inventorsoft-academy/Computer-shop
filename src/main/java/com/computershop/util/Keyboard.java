package com.computershop.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Keyboard {
    public static String input() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputData = reader.readLine();
            return inputData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
