package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.presentation.MainScreen.MainScreen
import com.example.apiintegration.presentation.auth.CheckDataScreen
import com.example.apiintegration.presentation.auth.FetchDataFromRoom

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(route = Screen.MainScreen.route) {
        MainScreen(navController)
    }

    composable(route = Screen.FetchDataFromRoom.route) {
        FetchDataFromRoom()
    }

    composable(route = Screen.CheckDataScreen.route) {
        CheckDataScreen()
    }
}