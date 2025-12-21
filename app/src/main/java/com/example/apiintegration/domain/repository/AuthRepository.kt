package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
}
