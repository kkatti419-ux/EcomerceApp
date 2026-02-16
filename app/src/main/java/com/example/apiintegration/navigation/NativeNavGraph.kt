package com.example.apiintegration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.apiintegration.common.ui.permission.CameraPermissionScreen
import com.example.apiintegration.common.ui.permission.ContactsListScreen
import com.example.apiintegration.common.ui.permission.ContactsPermissionScreen

fun NavGraphBuilder.nativeGraph(navController: NavController){
    composable(route = Screen.CameraPermissionScreen.route){
        CameraPermissionScreen()
    }
    composable(route = Screen.ContactsPermissionScreen.route){
        ContactsPermissionScreen()
    }
    composable(route = Screen.ContactsListScreen.route){
        ContactsListScreen()
    }
}
