package com.jfb.digitalbanking.data.remote

import com.jfb.digitalbanking.data.model.LoginRequest
import com.jfb.digitalbanking.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}