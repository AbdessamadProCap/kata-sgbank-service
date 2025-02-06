package com.kata.sgbankservice.exceptionshandlers;


import lombok.Setter;

@Setter
public class AccountNotFoundException extends RuntimeException {
    private String message;

    public AccountNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
