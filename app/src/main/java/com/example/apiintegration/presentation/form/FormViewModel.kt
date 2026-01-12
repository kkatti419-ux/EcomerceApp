package com.example.apiintegration.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.LocalStorage.LocalUserCredentials
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.usecase.local_storage.GetLocalUserCredentialsUseCase
import com.example.apiintegration.domain.usecase.local_storage.UpdateLocalUserCredentialsUseCase
import com.example.apiintegration.domain.usecase.local_user.DeleteUserUseCase
import com.example.apiintegration.domain.usecase.local_user.GetAllUsersUseCase
import com.example.apiintegration.domain.usecase.local_user.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val updateLocalUserCredentialsUseCase: UpdateLocalUserCredentialsUseCase,
    private val getLocalUserCredentialsUseCase: GetLocalUserCredentialsUseCase,
) : ViewModel() {

    private val _credentials = MutableStateFlow<LocalUserCredentials?>(null)
    val credentials: StateFlow<LocalUserCredentials?> = _credentials

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun loadUserCredentials() {
        _credentials.value = getLocalUserCredentialsUseCase()
    }

    fun updateUserCredentials(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                updateLocalUserCredentialsUseCase(
                    username = username,
                    firstname = firstname,
                    lastname = lastname,
                    phone = phone,
                    email = email,
                    profileImage = profileImage,
                    gender = gender
                )
                _uiState.value = ProfileUiState.Success
            } catch (e: Exception) {
                _uiState.value =
                    ProfileUiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}

sealed class ProfileUiState {
    object Idle : ProfileUiState()
    object Loading : ProfileUiState()
    object Success : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}
