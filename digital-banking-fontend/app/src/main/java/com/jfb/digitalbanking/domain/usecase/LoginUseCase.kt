package com.jfb.digitalbanking.domain.usecase

import com.jfb.digitalbanking.data.model.LoginRequest
import com.jfb.digitalbanking.data.model.LoginResponse
import com.jfb.digitalbanking.data.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {

    suspend fun execute(loginRequest: LoginRequest): Result<LoginResponse> {
        return userRepository.login(loginRequest)
    }
}