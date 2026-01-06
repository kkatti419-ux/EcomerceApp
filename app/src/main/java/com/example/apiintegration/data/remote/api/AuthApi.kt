package com.example.apiintegration.data.remote.api

import com.example.apiintegration.data.remote.dto.LoginRequest
import com.example.apiintegration.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun authentication(
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: LoginRequest,
    ): LoginResponse

}


// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.