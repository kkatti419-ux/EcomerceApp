package com.example.apiintegration.data.remote.api

import com.example.apiintegration.domain.model.product.ProductResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@retrofit2.http.Path("id") id: Long): com.example.apiintegration.domain.model.product.Product

}


// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.