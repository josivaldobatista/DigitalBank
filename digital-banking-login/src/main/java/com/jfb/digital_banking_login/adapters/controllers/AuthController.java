package com.jfb.digital_banking_login.adapters.controllers;

import com.jfb.digital_banking_login.adapters.controllers.request.LoginRequest;
import com.jfb.digital_banking_login.adapters.controllers.response.LoginResponse;
import com.jfb.digital_banking_login.application.ports.in.LoginUseCase;
import com.jfb.digital_banking_login.domains.exceptions.InvalidIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws InvalidIdentifierException {
        LoginResponse loginResponse = loginUseCase.execute(loginRequest);
        return ResponseEntity.ok(new LoginResponse(loginResponse.token()));
    }
}
