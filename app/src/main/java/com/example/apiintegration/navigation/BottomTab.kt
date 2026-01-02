package com.example.apiintegration.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomTab(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomTab("home", Icons.Outlined.Home, "Home")
    object Favorites : BottomTab("fav", Icons.Outlined.FavoriteBorder, "Fav")
    object Orders : BottomTab("orders", Icons.Outlined.List, "Orders")
    object Cart : BottomTab("cart", Icons.Outlined.ShoppingCart, "Cart")
    object Profile : BottomTab("profile", Icons.Outlined.Person, "Profile")
}
