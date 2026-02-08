package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.data.local.datasource.IntroductionPagesLists.introPages
import com.example.apiintegration.presentation.Intro.IntroScreen
import com.example.apiintegration.presentation.androidIntents.OpenBrowserScreen
import com.example.apiintegration.presentation.androidIntents.OpenDialerScreen
import com.example.apiintegration.presentation.androidIntents.ShareTextScreen
import com.example.apiintegration.presentation.starter.AppStartScreen

fun NavGraphBuilder.introNavGraph(navController: NavController) {

    composable(route= Screen.ShareTextScreen.route){
        ShareTextScreen(navController)
    }
    composable(route= Screen.OpenDialerScreen.route){
        OpenDialerScreen(navController)
    }

    composable(route= Screen.OpenBrowserScreen.route){
        OpenBrowserScreen(navController)
    }


    composable(route = Screen.AppStartScreen.route) {
        AppStartScreen(navController)
    }

    composable(route = Screen.IntroScreen.route){
        IntroScreen(
            pages = introPages,
            onFinish = {
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(Screen.IntroScreen.route) {
                        inclusive = true
                    }
                }
            }
        )

    }

}