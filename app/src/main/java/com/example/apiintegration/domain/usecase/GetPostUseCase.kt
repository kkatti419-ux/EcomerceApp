package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): Result<Post> {
        return repository.getPost()
    }
}
