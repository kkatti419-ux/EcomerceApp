package com.example.apiintegration.domain.usecase.auth

import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        return repository.login(username, password)
    }
}