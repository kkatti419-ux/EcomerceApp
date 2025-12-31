package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserDetailReposirotory {

    suspend fun upsertData(userData: UserDetails);
    fun getAllUsers(): Flow<List<UserDetails>>

}