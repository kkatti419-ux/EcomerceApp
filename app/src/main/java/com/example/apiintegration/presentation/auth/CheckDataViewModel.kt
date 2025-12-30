package com.example.apiintegration.presentation.auth

import androidx.lifecycle.ViewModel
import com.example.apiintegration.domain.usecase.GetAccessTokenUseCase
import com.example.apiintegration.domain.usecase.GetSavedCredentialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CheckDataViewModel @Inject constructor(
    private val getSavedCredentialsUseCase: GetSavedCredentialsUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CheckDataUiState>(CheckDataUiState.Loading)
    val uiState: StateFlow<CheckDataUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        try {
            val credentials = getSavedCredentialsUseCase()
            val token = getAccessTokenUseCase()
            _uiState.value = CheckDataUiState.Success(
                username = credentials.username ?: "Not set",
                password = credentials.password ?: "Not set",
                accessToken = token ?: "Not set",
//                phone = credentials.phone ?: "Not set"
            )
        } catch (e: Exception) {
            _uiState.value = CheckDataUiState.Error(e.message ?: "Unknown error occurred")
        }
    }
}

sealed class CheckDataUiState {
    object Loading : CheckDataUiState()
    data class Success(
        val username: String,
        val password: String,
        val accessToken: String,
//        val phone:String
    ) : CheckDataUiState()
    data class Error(val message: String) : CheckDataUiState()
}

