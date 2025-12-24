package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {
    suspend fun upsertUser(user: UserProfile)
    suspend fun deleteUser(user: UserProfile)
    fun getAllUsers(): Flow<List<UserProfile>>
}
