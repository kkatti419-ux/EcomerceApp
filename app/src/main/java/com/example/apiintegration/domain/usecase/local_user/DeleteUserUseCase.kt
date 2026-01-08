package com.example.apiintegration.domain.usecase.local_user

import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.ProfileLocalRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: ProfileLocalRepository
) {
    suspend operator fun invoke(user: UserProfile) {
        repository.deleteUser(user)
    }
}
