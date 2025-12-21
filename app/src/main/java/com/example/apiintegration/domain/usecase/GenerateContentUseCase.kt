package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.GeminiRepository
import javax.inject.Inject

class GenerateContentUseCase @Inject constructor(
    private val repository: GeminiRepository
) {
    suspend operator fun invoke(prompt: String): Result<String> {
        return repository.generateContent(prompt)
    }
}
