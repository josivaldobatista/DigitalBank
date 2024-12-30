package com.jfb.digital_banking_login.application.ports.in;

import com.jfb.digital_banking_login.adapters.controllers.request.LoginRequest;
import com.jfb.digital_banking_login.adapters.controllers.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse execute(LoginRequest loginRequest);
}
