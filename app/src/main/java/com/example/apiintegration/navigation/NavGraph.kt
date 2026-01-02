package com.example.apiintegration.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apiintegration.presentation.auth.AuthScreen
import com.example.apiintegration.presentation.auth.CheckDataScreen
import com.example.apiintegration.presentation.auth.FetchDataFromRoom
import com.example.apiintegration.presentation.auth.SignInScreen
import com.example.apiintegration.presentation.auth.StartViewModel
import com.example.apiintegration.presentation.form.ProfileScreen
import com.example.apiintegration.presentation.posts.PostScreen
import com.example.apiintegration.presentation.products.ProductDetailScreen
import com.example.apiintegration.presentation.products.ProductList
import com.example.apiintegration.presentation.starter.AppStartScreen
import com.example.apiintegration.presentation.todo.Todo

@Composable
fun AppNavGraph(startViewModel: StartViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    val startDestination by startViewModel.startDestination.collectAsState()

    NavHost(navController = navController, startDestination = Screen.AppStartScreen.route) {


        composable(route = Screen.AppStartScreen.route) {
            AppStartScreen(navController)
        }

        composable(route = Screen.FetchDataFromRoom.route) {
            FetchDataFromRoom()
        }


        composable(route = Screen.ProductList.route) {
            ProductList(navController)
        }
        composable(route = Screen.CheckDataScreen.route) {
            CheckDataScreen()
        }
        composable(
            route = Screen.ProductDetailScreen.route,
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
            ProductDetailScreen(productId = productId, navController = navController)
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(route = Screen.StartScreen.route) {
            SignInScreen(navController, onLoginSuccess = { username, email ->
                try {
                    navController.navigate(Screen.Home.createRoute(username, email))
                } catch (e: Exception) {
                    e.printStackTrace()

                }

            });
        }


//        composable(Screen.Auth.route) {
//            AuthScreen(
//                onForgotPassword = { value ->
//                    try {
//                        navController.navigate(Screen.Home.createRoute(value)) {
//                        }
//
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                },
////                onLoginSuccess = { username -> }
//                onLoginSuccess = { username ->
//                    // Wrap navigation in try-catch to prevent Binder transaction failures
//                    try {
//                        navController.navigate(Screen.Home.createRoute(username)) {
//                            popUpTo(Screen.Auth.route) { inclusive = true }
//                            // Avoid multiple copies
//                            launchSingleTop = true
//                            // Restore state if available
//                            restoreState = true
//                        }
//                    } catch (e: Exception) {
//                        // Log the error but don't crash
//                        e.printStackTrace()
//                    }
//                }
//            )
//        }
        composable(
            route = Screen.Home.route,
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Unknown"
            // Pass username to PostScreen and provide logout handling
            PostScreen(
                username = username,
                onLogout = {
                    navController.navigate(Screen.StartScreen.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.Todo.route) {
            Todo(navController)
        }
    }
}

