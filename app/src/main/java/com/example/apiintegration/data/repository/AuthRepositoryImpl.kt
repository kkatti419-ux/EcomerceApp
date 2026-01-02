package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.UserPreferences
import com.example.apiintegration.data.local.session.SessionManager
import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.data.remote.dto.LoginRequest
import com.example.apiintegration.data.remote.dto.LoginResponse
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: GeminiApi,
    private val userPreferences: UserPreferences,
    private val session: SessionManager
) : AuthRepository {

    override fun isUserLoggedIn(): Boolean {
        return session.isLoggedIn()
    }

    override suspend  fun  login(username: String, password: String): Result<User> {
        return try {
            val request = LoginRequest(
                username = username,
                password = password,
                expiresInMins = 30 // Default value
            )
            val response = api.authentication("application/json", request)

            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCountries(): List<Country> {
         return com.example.apiintegration.data.local.CountryDataSource.asianCountries
    }

    override suspend fun saveCredentials(username: String, password: String,phone:String) {
        userPreferences.saveCredentials(username, password,phone)
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String) {
        userPreferences.saveToken(accessToken, refreshToken)
    }

    override fun getSavedUsername(): String? {
        return userPreferences.getUsername()
    }

    override fun getPhone(): String? {
        return userPreferences.getPhoneNumber()
    }

    override fun getSavedPassword(): String? {
        return userPreferences.getPassword()
    }

    override fun getAccessToken(): String? {
        return userPreferences.getAccessToken()
    }

    override fun getRefreshToken(): String? {
        return userPreferences.getRefreshToken()
    }

    private fun LoginResponse.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            image = image,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
