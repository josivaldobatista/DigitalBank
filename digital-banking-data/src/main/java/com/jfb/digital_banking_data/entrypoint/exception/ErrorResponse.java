package com.jfb.digital_banking_data.entrypoint.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponse {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
