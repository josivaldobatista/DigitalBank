package com.jfb.digital_banking_gateway.adapters.exceptions;

public class ErrorMessageHolder {

    private static String errorMessage;

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        ErrorMessageHolder.errorMessage = errorMessage;
    }

    public static void clear() {
        errorMessage = null;
    }
}
