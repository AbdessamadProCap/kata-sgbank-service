package com.kata.sgbankservice.exceptionshandlers;

import lombok.Setter;

@Setter
public class UnknownAccountIdException extends RuntimeException {
    private String message;

    public UnknownAccountIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
