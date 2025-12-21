package com.example.apiintegration.domain.repository

import com.example.apiintegration.data.remote.dto.PostResponse

interface PostRepository {
    suspend fun getPosts(): Result<List<PostResponse>>
    suspend fun getPost(): Result<PostResponse>
}
