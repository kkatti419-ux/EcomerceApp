package com.example.apiintegration.presentation.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.usecase.GetCountriesUseCase
import com.example.apiintegration.domain.usecase.LoginUseCase
import com.example.apiintegration.domain.usecase.SaveCredentialsUseCase
import com.example.apiintegration.domain.usecase.SaveTokenUseCase
import com.example.apiintegration.domain.usecase.local_user.UserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveCredentialsUseCase: SaveCredentialsUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val userDetailsUseCase: UserDetailsUseCase,
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _profileImage = MutableStateFlow<Uri?>(null)
    val profileImage = _profileImage.asStateFlow()

    init {
        fetchCountries()
    }

    fun onProfileImageSelected(uri: Uri) {
        _profileImage.value = uri
    }

    fun createAccount(username: String, password: String,phone:String) {
        if (username.isBlank() || password.isBlank()) return
        
        viewModelScope.launch {
            saveCredentialsUseCase(username, password,phone)
            // Here you would typically also call a repository function to create the account on the server
            // For now, we simulate success or just save locally as requested
        }
    }


    fun fetchCountries() {
        viewModelScope.launch {
            try {
                _countries.value = getCountries()
            } catch (e: Exception) {
                _countries.value = emptyList()
            }
        }
    }
    suspend fun getCountries(): List<Country> {
        return try {
            getCountriesUseCase()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun validateLoginWithPhone(country: Country?, mobile: String): Boolean {
        if (country == null) {
            _uiState.value = AuthUiState.Error("Country code is required")
            return false
        }
        if (mobile.isBlank()) {
            _uiState.value = AuthUiState.Error("Mobile number is required")
            return false
        }
        // Basic validation: ensure mobile contains only digits (or matches a pattern if specific)
        if (!mobile.all { it.isDigit() }) {
            _uiState.value = AuthUiState.Error("Mobile number must contain only digits")
            return false
        }
        return true
    }






    fun sendPrompt(username: String, password: String) {

//        Temp data for room database

        if (username.isBlank() || password.isBlank()) return

        _uiState.value = AuthUiState.Loading

        viewModelScope.launch {
            try {
                val result = loginUseCase.invoke(username, password)
                result.onSuccess { user ->
                    // Save token after successful login
                    saveTokenUseCase(user.accessToken, user.refreshToken)
                    val userd = UserDetails(
                        id = 1,
                        username = username,
                        password = password,
                        token = user.accessToken
                    )

                    userDetailsUseCase.upsertData(userd)
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