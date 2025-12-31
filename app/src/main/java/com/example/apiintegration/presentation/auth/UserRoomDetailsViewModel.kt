package com.example.apiintegration.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.usecase.local_user.UserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRoomDetailsViewModel @Inject constructor(
    private val userDetailsUseCase: UserDetailsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UserRoomUiState>(UserRoomUiState.Loading)
    val uiState: StateFlow<UserRoomUiState> = _uiState.asStateFlow()

    init {
        getAllUsers()
    }
    private fun getAllUsers() {
        viewModelScope.launch {
            try {
                userDetailsUseCase.invoke().collect { users ->
                    _uiState.value = UserRoomUiState.Success(users)
                }
            } catch (e: Exception) {
                _uiState.value =
                    UserRoomUiState.Error(e.message ?: "Failed to load users")
            }
        }
    }
//
//    fun deleteUser(userId: Int) {
//        viewModelScope.launch {
//            userDetailsUseCase.deleteUser(userId)
//        }
//    }
//
//    fun getUserById(userId: Int, onResult: (UserData?) -> Unit) {
//        viewModelScope.launch {
//            onResult(userDetailsUseCase.getUserById(userId))
//        }
//    }
}


sealed class UserRoomUiState {

    object Loading : UserRoomUiState()

    data class Success(
        val users: List<UserDetails>
    ) : UserRoomUiState()

    data class Error(
        val message: String
    ) : UserRoomUiState()
}
