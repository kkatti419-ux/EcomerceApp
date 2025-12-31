package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.dao.UserDetailsDao
import com.example.apiintegration.data.mapper.toDomain
import com.example.apiintegration.data.mapper.toEntity
import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.UserDetailReposirotory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(val userDetailsDao: UserDetailsDao) : UserDetailReposirotory {
    override suspend fun upsertData(userData: UserDetails) {
        val result= userData.toEntity()

        userDetailsDao.insertData(result)
    }

    override fun getAllUsers(): Flow<List<UserDetails>> {
        return userDetailsDao.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

}