package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.apiintegration.presentation.posts.PostScreen

fun NavGraphBuilder.postNavGraph(navController: NavController) {

    composable(
        route = Screen.Home.route,
        arguments = listOf(
            navArgument("username") { type = NavType.StringType },
            navArgument("email") { type = NavType.StringType })) { backStackEntry ->
        val username = backStackEntry.arguments?.getString("username") ?: "Unknown"
        // Pass username to PostScreen and provide logout handling
        PostScreen(
            username = username, onLogout = {
                navController.navigate(Screen.StartScreen.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            })

    }
}