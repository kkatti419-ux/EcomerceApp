package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.dao.UserDao
import com.example.apiintegration.data.local.entity.UserEntity
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : LocalUserRepository {

    override suspend fun upsertUser(user: UserProfile) {
        dao.upsertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: UserProfile) {
        dao.deleteUser(user.toEntity())
    }

    override fun getAllUsers(): Flow<List<UserProfile>> {
        return dao.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    private fun UserProfile.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            firstName = firstName,
            lastName = lastName,
            place = place,
            age = age

        )
    }

    private fun UserEntity.toDomain(): UserProfile {
        return UserProfile(
            id = id,
            firstName = firstName,
            lastName = lastName,
            place = place,
            age = age
        )
    }
}
