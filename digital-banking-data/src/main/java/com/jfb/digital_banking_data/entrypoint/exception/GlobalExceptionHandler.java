package com.jfb.digital_banking_data.entrypoint.exception;

import com.jfb.digital_banking_data.core.exception.InsufficientFundsException;
import com.jfb.digital_banking_data.core.exception.ObjectAlreadyExistsException;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> objectAlreadyExistsException(ObjectAlreadyExistsException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Conflito de recurso.");
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Erro na validação.");
        error.setMessage("Validation failed");
        error.setPath(request.getRequestURI());
        error.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Erro na validação.");
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        error.setErrors(ex.getErrors());

        return ResponseEntity.status(status).body(error);
    }
}
