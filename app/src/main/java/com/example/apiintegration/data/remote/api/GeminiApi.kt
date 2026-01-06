package com.example.apiintegration.data.remote.api

import com.example.apiintegration.data.remote.dto.GeminiRequest
import com.example.apiintegration.data.remote.dto.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GeminiApi {

    @POST("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
    suspend fun generateContent(
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiRequest,
    ): GeminiResponse

}


// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.