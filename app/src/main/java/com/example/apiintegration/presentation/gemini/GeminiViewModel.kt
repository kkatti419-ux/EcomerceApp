package com.example.apiintegration.presentation.gemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.usecase.GenerateContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeminiViewModel @Inject constructor(
    private val generateContentUseCase: GenerateContentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GeminiUiState>(GeminiUiState.Idle)
    val uiState: StateFlow<GeminiUiState> = _uiState.asStateFlow()

    fun sendPrompt(prompt: String) {
        if (prompt.isBlank()) return

        _uiState.value = GeminiUiState.Loading

        viewModelScope.launch {
            try {
                val result = generateContentUseCase(prompt)
                result.onSuccess { responseText ->
                    _uiState.value = GeminiUiState.Success(responseText)
                }.onFailure { error ->
                    _uiState.value = GeminiUiState.Error(error.message ?: "Unknown error occurred")
                }
            } catch (e: Exception) {
                // Handle cancellation gracefully during activity destruction
                if (e !is kotlinx.coroutines.CancellationException) {
                    _uiState.value = GeminiUiState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
}

sealed class GeminiUiState {
    object Idle : GeminiUiState()
    object Loading : GeminiUiState()
    data class Success(val response: String) : GeminiUiState()
    data class Error(val message: String) : GeminiUiState()
}
