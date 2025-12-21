package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.data.remote.dto.GeminiRequest
import com.example.apiintegration.data.remote.dto.Content
import com.example.apiintegration.data.remote.dto.Part
import com.example.apiintegration.domain.repository.GeminiRepository
import javax.inject.Inject

class GeminiRepositoryImpl @Inject constructor(
    private val api: GeminiApi
) : GeminiRepository {
    // TODO: Replace with your actual Gemini API Key.
    // In a production app, this should be stored in local.properties and accessed via BuildConfig.
    private val apiKey = "AIzaSyC0DVfxZwLoIVxO56Uz-Zg1_7ejVgeYQlU"

    override suspend fun generateContent(prompt: String): Result<String> {
        return try {
            val request = GeminiRequest(
                contents = listOf(
                    Content(parts = listOf(Part(text = prompt)))
                )
            )
            val response = api.generateContent(apiKey, request)
            
            // Extract the text from the response
            val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: "No response generated."
            
            Result.success(text)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
