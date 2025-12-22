package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.data.remote.dto.PostResponse
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: GeminiApi
) : PostRepository {

    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val wrapper = api.getPostsWrapper()
            Result.success(wrapper.posts.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPost(id: Int): Result<Post> {
        return try {
            val post = api.getPostById(id)
            Result.success(post.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun PostResponse.toDomain(): Post {
        return Post(
            id = id,
            title = title,
            body = body,
            tags = tags,
            likes = reactions.likes,
            dislikes = reactions.dislikes,
            views = views,
            userId = userId
        )
    }
}
