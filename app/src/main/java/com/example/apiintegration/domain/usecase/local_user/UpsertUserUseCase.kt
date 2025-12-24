package com.example.apiintegration.domain.usecase.local_user

import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.LocalUserRepository
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val repository: LocalUserRepository
) {
    suspend operator fun invoke(user: UserProfile) {
        repository.upsertUser(user)
    }
}
