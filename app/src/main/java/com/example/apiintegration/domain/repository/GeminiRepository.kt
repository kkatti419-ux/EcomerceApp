package com.example.apiintegration.domain.repository

interface GeminiRepository {
    suspend fun generateContent(prompt: String): Result<String>
}
