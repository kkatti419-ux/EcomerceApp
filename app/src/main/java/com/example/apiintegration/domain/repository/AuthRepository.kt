package com.example.apiintegration.domain.repository

import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.domain.model.User

interface AuthRepository {
    suspend fun  login(username: String, password: String): Result<User>

    fun isUserLoggedIn(): Boolean


    fun getCountries(): List<Country>

    suspend fun saveCredentials(username: String, password: String,phone:String)
    
    suspend fun saveToken(accessToken: String, refreshToken: String)
    
    fun getSavedUsername(): String?

    fun getPhone():String?
    
    fun getSavedPassword(): String?
    
    fun getAccessToken(): String?
    
    fun getRefreshToken(): String?
}
