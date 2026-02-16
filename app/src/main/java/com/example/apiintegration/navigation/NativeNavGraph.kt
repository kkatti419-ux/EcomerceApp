package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.apiintegration.common.ui.permission.BluetoothScanScreen
import com.example.apiintegration.common.ui.permission.CameraPermissionScreen
import com.example.apiintegration.common.ui.permission.ContactsListScreen
import com.example.apiintegration.common.ui.permission.ContactsPermissionScreen
import com.example.apiintegration.common.ui.permission.WhatsAppScreen

fun NavGraphBuilder.nativeGraph(navController: NavController){
    composable(route = Screen.CameraPermissionScreen.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "myapp://camera"
            }
        )
    ){
        CameraPermissionScreen()
    }
    composable(route = Screen.ContactsPermissionScreen.route){
        ContactsPermissionScreen()
    }
    composable(route = Screen.ContactsListScreen.route){
        ContactsListScreen()
    }
    composable(route = Screen.WhatsAppScreen.route){
        WhatsAppScreen()
    }
    composable(route = Screen.BluetoothScanScreen.route){
        BluetoothScanScreen()
    }
}
