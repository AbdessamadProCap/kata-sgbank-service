package com.kata.sgbankservice.exceptionshandlers;

import lombok.Setter;

@Setter
public class AccountOperationIsNullException extends RuntimeException {
    private String message;

    public AccountOperationIsNullException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
