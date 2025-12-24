package com.example.apiintegration.presentation.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _profileImage = MutableStateFlow<Uri?>(null)
    val profileImage = _profileImage.asStateFlow()

    fun onProfileImageSelected(uri: Uri) {
        _profileImage.value = uri
    }


    fun sendPrompt(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) return

        _uiState.value = AuthUiState.Loading

        viewModelScope.launch {
            try {
                val result = loginUseCase(username, password)
                result.onSuccess { user ->
                    _uiState.value = AuthUiState.Success(user)
                }.onFailure { error ->
                    _uiState.value = AuthUiState.Error(error.message ?: "Unknown error occurred")
                }
            } catch (e: Exception) {
                // Handle cancellation gracefully during activity destruction
                if (e !is kotlinx.coroutines.CancellationException) {
                    _uiState.value = AuthUiState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: User) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}