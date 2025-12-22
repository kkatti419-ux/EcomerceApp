package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPost(id: Int): Result<Post>
}
