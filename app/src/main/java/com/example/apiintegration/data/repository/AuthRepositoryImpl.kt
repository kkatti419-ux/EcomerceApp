package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.UserPreferences
import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.data.remote.dto.LoginRequest
import com.example.apiintegration.data.remote.dto.LoginResponse
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: GeminiApi,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> {
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

    override suspend fun saveCredentials(username: String, password: String) {
        userPreferences.saveCredentials(username, password)
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
