package com.example.apiintegration.data.remote.api

import com.example.apiintegration.data.remote.dto.PostResponse
import com.example.apiintegration.data.remote.dto.PostsWrapper
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPostsWrapper(): PostsWrapper

    @GET("posts/{id}")
    suspend fun getPostById(@retrofit2.http.Path("id") id: Int): PostResponse
}


// This interface defines the API endpoints for Gemini.
// We use Retrofit annotations to map the HTTP request to a Kotlin function.
// This abstraction allows us to make network calls like simple function calls.