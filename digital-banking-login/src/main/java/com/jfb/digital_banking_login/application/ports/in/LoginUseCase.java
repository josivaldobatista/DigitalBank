package com.jfb.digital_banking_login.application.ports.in;

import com.jfb.digital_banking_login.adapters.controllers.request.LoginRequest;

public interface LoginUseCase {
    String execute(LoginRequest loginRequest);
}
