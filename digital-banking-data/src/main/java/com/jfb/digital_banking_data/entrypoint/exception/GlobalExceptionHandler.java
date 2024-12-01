package com.jfb.digital_banking_data.entrypoint.exception;

import com.jfb.digital_banking_data.core.exception.InsufficientFundsException;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Recurso não encontrado.");
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> insufficientFundsException(InsufficientFundsException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Fundos insuficiente.");
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
