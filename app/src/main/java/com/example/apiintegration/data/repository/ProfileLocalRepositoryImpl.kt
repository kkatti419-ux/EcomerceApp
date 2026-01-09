package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.UserPreferencesStorage
import com.example.apiintegration.data.local.dao.UserDao
import com.example.apiintegration.data.local.entity.UserEntity
import com.example.apiintegration.domain.model.LocalStorage.LocalUserCredentials
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.ProfileLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileLocalRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val authLocalDataSource: UserPreferencesStorage,

    ) : ProfileLocalRepository {

    override suspend fun upsertUser(user: UserProfile) {
//        dao.upsertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: UserProfile) {
//        dao.deleteUser(user.toEntity())
    }

    override fun getAllUsers(): Flow<List<UserProfile>> {
        return dao.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveTokenToLocalStorage(accessToken: String, refreshToken: String) {
        authLocalDataSource.saveToken(accessToken, refreshToken)
    }

    override fun getUsernameFromLocalStorage(): String? {
        return authLocalDataSource.getUsername()
    }

    override fun getFirstNameFromLocalStorage(): String? {
        return authLocalDataSource.getFirstName()
    }

    override fun getLastNameFromLocalStorage(): String? {
        return authLocalDataSource.getLastName()
    }

    override fun getEmailFromLocalStorage(): String? {
        return authLocalDataSource.getEmail()
    }

    override fun getPhoneFromLocalStorage(): String? {
        return authLocalDataSource.getPhoneNumber()
    }

    override fun getSavedPasswordFromLocalStorage(): String? {
        return authLocalDataSource.getPassword()
    }

    override fun getAccessTokenFromLocalStorage(): String? {
        return authLocalDataSource.getAccessToken()
    }

    override fun getRefreshTokenFromLocalStorage(): String? {
        return authLocalDataSource.getRefreshToken()
    }

    override suspend fun saveCredentialsToLocalStorage(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ) {
        authLocalDataSource.saveCredentials(
            username = username,
            firstname = firstname,
            lastname = lastname,
            phone = phone,
            email = email,
            profileImage = profileImage,
            gender = gender
        )
    }

    override fun getLocalUserCredentials(): LocalUserCredentials {
        return LocalUserCredentials(
            username = authLocalDataSource.getUsername(),
            firstName = authLocalDataSource.getFirstName(),
            lastName = authLocalDataSource.getLastName(),
            phone = authLocalDataSource.getPhoneNumber(),
            email = authLocalDataSource.getEmail(),
            profileImage = authLocalDataSource.getProfileImage(),
            gender = authLocalDataSource.getGender()
        )
    }

    override fun updateUserCredentials(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ) {
        authLocalDataSource.saveCredentials(
            username = username,
            firstname = firstname,
            lastname = lastname,
            phone = phone,
            email = email,
            profileImage = profileImage,
            gender = gender
        )
    }



//    private fun UserProfile.toEntity(): UserEntity {
//        return UserEntity(
//            id = id, firstName = firstName, lastName = lastName, place = place, age = age
//
//        )
//    }

    private fun UserEntity.toDomain(): UserProfile {
        return UserProfile(
            id = id, firstName = firstName, lastName = lastName, place = place, age = age
        )
    }
}
