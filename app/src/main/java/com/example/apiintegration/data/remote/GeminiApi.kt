package com.example.apiintegration.data.remote

import com.example.apiintegration.data.remote.dto.PostResponse
import com.example.apiintegration.data.remote.dto.LoginRequest
import com.example.apiintegration.data.remote.dto.LoginResponse
import com.example.apiintegration.data.remote.dto.GeminiRequest
import com.example.apiintegration.data.remote.dto.GeminiResponse
import com.example.apiintegration.data.remote.dto.PostsWrapper
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.Root
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// GeminiApi.kt
// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.

interface GeminiApi {
    // We use the full URL here to override the base URL defined in the Retrofit instance.
    // This is useful when integrating with multiple APIs that have different base URLs.
    @POST("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
    suspend fun generateContent(
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse

//    @GET("https://dummyjson.com/posts?=")
//    suspend fun generatePosts(
//    ): List<Root>

//    @GET("https://jsonplaceholder.typicode.com/posts/1")
//      suspend fun getPost(
//      ): Post


    @POST("https://dummyjson.com/auth/login")
    suspend fun authentication(
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: LoginRequest
    ): LoginResponse


    @GET("posts")
    suspend fun getPostsWrapper(): PostsWrapper

    // if you also have endpoint for single post:
    @GET("posts/{id}")
    suspend fun getPostById(@retrofit2.http.Path("id") id: Int): PostResponse


}
