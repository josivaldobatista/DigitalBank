package com.jfb.digital_banking_login.adapters.controllers;

import com.jfb.digital_banking_login.application.ports.in.CreateUserUseCase;
import com.jfb.digital_banking_login.adapters.controllers.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        createUserUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}