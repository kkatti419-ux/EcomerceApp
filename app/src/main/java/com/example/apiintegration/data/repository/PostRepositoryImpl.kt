package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.data.remote.dto.PostResponse
import com.example.apiintegration.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: GeminiApi
) : PostRepository {

    override suspend fun getPosts(): Result<List<PostResponse>> {
        return try {
            val wrapper = api.getPostsWrapper()
            Result.success(wrapper.posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPost(id: Int): Result<PostResponse> {
        return try {
            val post = api.getPostById(id)
            Result.success(post)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

