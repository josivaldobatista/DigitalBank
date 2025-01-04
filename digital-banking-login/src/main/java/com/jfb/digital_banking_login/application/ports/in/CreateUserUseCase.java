package com.jfb.digital_banking_login.application.ports.in;

import com.jfb.digital_banking_login.adapters.controllers.request.CreateUserRequest;

public interface CreateUserUseCase {

    void execute(CreateUserRequest request);
}