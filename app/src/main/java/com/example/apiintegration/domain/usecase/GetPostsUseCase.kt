package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): Result<List<Post>> {
        return repository.getPosts()
    }
}
