package com.jfb.digital_banking_gateway.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jfb.digital_banking_gateway.exception.ErrorResponse;
import com.jfb.digital_banking_gateway.core.usecase.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public FeignErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            InputStream body = response.body().asInputStream();
            String bodyStr = new String(body.readAllBytes(), StandardCharsets.UTF_8);
            ErrorResponse errorResponse = objectMapper.readValue(bodyStr, ErrorResponse.class);
            if (response.status() == HttpStatus.NOT_FOUND.value()) {
                return new ResourceNotFoundException(errorResponse.getMessage());
            }
            return new RuntimeException(errorResponse.getMessage());
        } catch (IOException e) {
            return new RuntimeException("Erro inesperado durante a decodificação da resposta de erro do Feign client", e);
        }
    }
}
