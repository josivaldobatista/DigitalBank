package com.jfb.digital_banking_gateway.adapters.exceptions;

public class CustomException extends RuntimeException {

    private final String errorMessage;

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
