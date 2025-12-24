package com.example.apiintegration.domain.usecase.local_user

import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: LocalUserRepository
) {
    operator fun invoke(): Flow<List<UserProfile>> {
        return repository.getAllUsers()
    }
}
