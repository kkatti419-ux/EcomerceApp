package com.example.apiintegration.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiintegration.presentation.MainScreen.MainScreen
//import com.example.apiintegration.presentation.auth.CheckDataScreen
import com.example.apiintegration.presentation.auth.FetchDataFromRoom
import com.example.apiintegration.presentation.auth.StartViewModel

@Composable
fun AppNavGraph(startViewModel: StartViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    val startDestination by startViewModel.startDestination.collectAsState()

    NavHost(navController = navController, startDestination = Screen.BluetoothScanScreen.route) {
        introNavGraph(navController)
        authNavGraph(navController)
        postNavGraph(navController)
        productNavGraph(navController)
        profileNavGraph(navController)
        mainNavGraph(navController)
        nativeGraph(navController)
    }
}


