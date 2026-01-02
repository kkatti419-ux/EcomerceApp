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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.apiintegration.navigation.BottomTab
import com.example.apiintegration.presentation.Home.ColorScreen
import com.example.apiintegration.presentation.Home.HomeTab

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf<BottomTab>(BottomTab.Home) }

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
                        selected = selectedTab.route == tab.route,
                        onClick = { selectedTab = tab },
                        icon = {
                            Icon(tab.icon, contentDescription = tab.label)
                        },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                BottomTab.Home -> HomeTab(navController)
                BottomTab.Favorites -> ColorScreen(Color(0xFFFFF3E0), "Favorites")
                BottomTab.Orders -> ColorScreen(Color(0xFFE3F2FD), "Orders")
                BottomTab.Cart -> ColorScreen(Color(0xFFE8F5E9), "Cart")
                BottomTab.Profile -> ColorScreen(Color(0xFFFCE4EC), "Profile")
            }
        }
    }
}
