package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.presentation.MainScreen.MainScreen
//import com.example.apiintegration.presentation.auth.CheckDataScreen
import com.example.apiintegration.presentation.auth.FetchDataFromRoom
import com.example.apiintegration.presentation.recipe.RecipeScreen
import com.example.apiintegration.presentation.recipe.RecipeScreenRoute

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(route = Screen.MainScreen.route) {
        MainScreen(navController)
    }

    composable(route = Screen.FetchDataFromRoom.route) {
        FetchDataFromRoom()
    }

    composable(
        route = Screen.RecipeDataScreen.route,
    ) {
        RecipeScreenRoute(
            navController
        )
    }

//    composable(route = Screen.CheckDataScreen.route) {
//        CheckDataScreen()
//    }
}