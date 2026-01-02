package com.example.apiintegration.presentation.auth

import androidx.lifecycle.ViewModel
import com.example.apiintegration.domain.usecase.CheckLoginStatusUseCase
import com.example.apiintegration.domain.usecase.GetSavedCredentialsUseCase
import com.example.apiintegration.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val checkLoginStatus: CheckLoginStatusUseCase,
    private val getSavedCredentialsUseCase: GetSavedCredentialsUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow(Screen.StartScreen.route)
    val startDestination: StateFlow<String> = _startDestination

    init {
        decideStartDestination()
    }

    private fun decideStartDestination() {
        val isLoggedIn = checkLoginStatus()
        if (isLoggedIn) {
            val credentials = getSavedCredentialsUseCase()
            _startDestination.value = Screen.ProductList.route
        } else {
            _startDestination.value = Screen.StartScreen.route
        }
    }
}
