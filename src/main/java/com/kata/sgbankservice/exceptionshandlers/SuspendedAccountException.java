package com.kata.sgbankservice.exceptionshandlers;

import lombok.Setter;

@Setter
public class SuspendedAccountException extends RuntimeException {
    private String message;

    public SuspendedAccountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
