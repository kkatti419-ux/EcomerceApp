package com.example.apiintegration.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// GeminiModels.kt
// This file contains the Data Transfer Objects (DTOs) for the Gemini API.
// We use Moshi for JSON parsing, hence the @JsonClass and @Json annotations.
// This separates the data layer from the domain/UI layer, ensuring type safety for API responses.

@JsonClass(generateAdapter = true)
data class GeminiRequest(
    @Json(name = "contents") val contents: List<Content>
)

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "parts") val parts: List<Part>
)

@JsonClass(generateAdapter = true)
data class Part(
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class GeminiResponse(
    @Json(name = "candidates") val candidates: List<Candidate>?
)

@JsonClass(generateAdapter = true)
data class Candidate(
    @Json(name = "content") val content: Content?,
    @Json(name = "finishReason") val finishReason: String?
)
