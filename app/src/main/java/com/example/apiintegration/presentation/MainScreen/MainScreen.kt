package com.example.apiintegration.presentation.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apiintegration.navigation.BottomTab
import com.example.apiintegration.presentation.Home.ColorScreen
import com.example.apiintegration.presentation.Home.HomeTab
import com.example.apiintegration.presentation.form.ProfileScreen

@Composable
fun MainScreen(rootNavController: NavController) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(
                    BottomTab.Home,
                    BottomTab.Favorites,
                    BottomTab.Orders,
                    BottomTab.Cart,
                    BottomTab.Profile
                ).forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            bottomNavController.navigate(tab.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(tab.icon, contentDescription = tab.label)
                        },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomTab.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomTab.Home.route) {
                // Pass root controller to HomeTab
                HomeTab(controller = rootNavController)
            }
            composable(BottomTab.Favorites.route) {
                ColorScreen(Color(0xFFFFF3E0), "Favorites")
            }
            composable(BottomTab.Orders.route) {
                ColorScreen(Color(0xFFE3F2FD), "Orders")
            }
            composable(BottomTab.Cart.route) {
                ColorScreen(Color(0xFFE8F5E9), "Cart")
            }
            composable(BottomTab.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
