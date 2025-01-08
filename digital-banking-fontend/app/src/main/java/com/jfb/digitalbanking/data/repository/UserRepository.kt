package com.jfb.digitalbanking.data.repository

import com.jfb.digitalbanking.data.model.LoginRequest
import com.jfb.digitalbanking.data.model.LoginResponse
import com.jfb.digitalbanking.data.remote.ApiService
import com.jfb.digitalbanking.data.remote.RetrofitClient

class UserRepository {

    private val apiService: ApiService = RetrofitClient.apiService

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = apiService.login(loginRequest)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}