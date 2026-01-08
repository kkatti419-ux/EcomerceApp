package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.presentation.auth.screens.OtpScreen
import com.example.apiintegration.presentation.auth.screens.SignInScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    composable(route = Screen.StartScreen.route) {
        SignInScreen(navController, onLoginSuccess = { username, email ->
            try {
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(Screen.StartScreen.route) { inclusive = true }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
    composable(route = Screen.OtpInputField.route) {
        OtpScreen(navController = navController)
    }
}
