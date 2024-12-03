package com.jfb.digital_banking_gateway.core.usecase.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
