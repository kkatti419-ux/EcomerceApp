package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.apiintegration.presentation.form.ProfileScreen
import com.example.apiintegration.presentation.products.ProductDetailScreen
import com.example.apiintegration.presentation.products.ProductList

fun NavGraphBuilder.productNavGraph(navController: NavController) {

    composable(
        route = Screen.ProductDetailScreen.route,
        arguments = listOf(navArgument("productId") { type = NavType.LongType })
    ) { backStackEntry ->
        val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
        ProductDetailScreen(productId = productId, navController = navController)
    }


    composable(route = Screen.ProductList.route) {
        ProductList(navController)
    }

}