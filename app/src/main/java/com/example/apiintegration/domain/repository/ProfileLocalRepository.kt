package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.LocalStorage.LocalUserCredentials
import com.example.apiintegration.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileLocalRepository {
    suspend fun upsertUser(user: UserProfile)
    suspend fun deleteUser(user: UserProfile)

    fun getAllUsers(): Flow<List<UserProfile>>

    suspend fun saveTokenToLocalStorage(accessToken: String, refreshToken: String)
    fun getUsernameFromLocalStorage(): String?

    fun getFirstNameFromLocalStorage(): String?

    fun getLastNameFromLocalStorage(): String?

    fun getEmailFromLocalStorage(): String?
    fun getPhoneFromLocalStorage(): String?
    fun getSavedPasswordFromLocalStorage(): String?
    fun getAccessTokenFromLocalStorage(): String?
    fun getRefreshTokenFromLocalStorage(): String?

    //IMPORTANT
    suspend fun saveCredentialsToLocalStorage(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    )

    fun getLocalUserCredentials(): LocalUserCredentials

    fun updateUserCredentials(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ):Unit


}

