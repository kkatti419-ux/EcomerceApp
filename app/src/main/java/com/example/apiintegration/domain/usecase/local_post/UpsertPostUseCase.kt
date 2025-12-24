package com.example.apiintegration.domain.usecase.local_post

import com.example.apiintegration.domain.model.PostList
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.LocalPostRepository
import javax.inject.Inject

class UpsertPostUseCase @Inject constructor(
    private val repository: LocalPostRepository,
) {
    suspend operator fun invoke(user: PostList) {
        repository.upsertPost(user)
    }
}