package com.perepelitsya.exception;

/**
 * Created by Andriu on 9/1/2017.
 */
public class JdbcCustomException extends RuntimeException {

    private String message;

    public JdbcCustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
