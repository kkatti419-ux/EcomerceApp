package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.presentation.form.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavController){
    composable(route = Screen.ProfileScreen.route) {
        ProfileScreen()
    }
}

