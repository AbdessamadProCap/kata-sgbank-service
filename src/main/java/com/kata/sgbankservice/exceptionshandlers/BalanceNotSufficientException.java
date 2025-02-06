package com.kata.sgbankservice.exceptionshandlers;


import lombok.Setter;

@Setter
public class BalanceNotSufficientException extends RuntimeException {
    private String message;

    public BalanceNotSufficientException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
