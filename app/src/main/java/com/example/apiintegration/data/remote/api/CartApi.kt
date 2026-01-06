package com.example.apiintegration.data.remote.api

import com.example.apiintegration.domain.model.Cart.CartResponse
import retrofit2.http.GET

interface CartApi {
    @GET(value = "carts")
    suspend fun getCarts(): CartResponse
}

// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.