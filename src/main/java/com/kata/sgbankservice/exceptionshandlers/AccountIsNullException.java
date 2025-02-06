package com.kata.sgbankservice.exceptionshandlers;

import lombok.Setter;

@Setter
public class AccountIsNullException extends RuntimeException {
    private String message;

    public AccountIsNullException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
