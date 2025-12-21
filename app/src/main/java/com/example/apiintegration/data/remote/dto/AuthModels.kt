package com.example.apiintegration.data.remote.dto

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "id") val id: Int,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "image") val image: String
)

