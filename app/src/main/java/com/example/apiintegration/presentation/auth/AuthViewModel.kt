package com.example.apiintegration.presentation.auth

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.usecase.GetCountriesUseCase
import com.example.apiintegration.domain.usecase.auth.AuthenticateUserUseCase
import com.example.apiintegration.domain.usecase.local_storage.StoreUserCredentialsLocallyUseCase
import com.example.apiintegration.domain.usecase.local_storage.StoreAuthTokensLocallyUseCase
import com.example.apiintegration.domain.usecase.local_user.UserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val storeUserCredentialsLocallyUseCase: StoreUserCredentialsLocallyUseCase,
    private val storeAuthTokensLocallyUseCase: StoreAuthTokensLocallyUseCase,
    private val userDetailsUseCase: UserDetailsUseCase,
    private val getCountriesUseCase: GetCountriesUseCase,
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


    fun readImageBytes(
        context: Context,
        uri: Uri?,
    ): ByteArray {
        if (uri == null) {
            throw IllegalStateException("Image URI is null")
        }
        return context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.readBytes()
        } ?: throw IllegalStateException("Cannot read image")
    }


    fun loginValidation(
        username: String, password: String, phone: String, profileImage: Uri?,
        context: Context,
    ) {
        AppLogger.d("--------------", "hello")
        AppLogger.d(profileImage.toString(), "profileImage")
        val imageBytes = readImageBytes(context = context, profileImage)
        AppLogger.d(imageBytes.toString(), "imageBytes")
        val requestBody = imageBytes.toRequestBody("image/*".toMediaType())
        AppLogger.d(requestBody.toString(), "requestBody")

        val multipartBody = MultipartBody.Part.createFormData(
            name = "profile_image",
            filename = "profile.jpg",
            body = requestBody
        )

        AppLogger.d(multipartBody.toString(), "multipartBody")


//        Temp data for room database
        if (username.isBlank() || password.isBlank()) return
        _uiState.value = AuthUiState.Loading
        viewModelScope.launch {
            try {
                val result = authenticateUserUseCase.invoke(username, password)
                result.onSuccess { user ->
                    AppLogger.d(user.toString(), "user")

                    storeAuthTokensLocallyUseCase(user.accessToken, user.refreshToken)

                    storeUserCredentialsLocallyUseCase(
                        username,
                        firstname = user.firstName,
                        lastname = user.lastName,
                        phone = phone,
                        email = user.email,
                        profileImage = user.image,
                        gender = user.gender
                    )

                    val userDetails = UserDetails(
                        id = 1,
                        username = username,
                        password = password,
                        token = user.accessToken,
                    )

                    userDetailsUseCase.upsertData(userDetails)
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