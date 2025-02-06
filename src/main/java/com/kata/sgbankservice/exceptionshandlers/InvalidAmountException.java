package com.kata.sgbankservice.exceptionshandlers;


import lombok.Setter;

@Setter
public class InvalidAmountException extends RuntimeException {
    private String message;

    public InvalidAmountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
