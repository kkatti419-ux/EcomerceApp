package com.example.apiintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.apiintegration.navigation.AppNavGraph
import com.example.apiintegration.ui.theme.ApiIntegrationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiIntegrationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // We pass the modifier with padding to the screen to handle edge-to-edge correctly
                    AppNavGraph()
                }
            }
        }
    }

    override fun onDestroy() {
        // Ensure proper cleanup before calling super to prevent DeadObjectException
        try {
            super.onDestroy()
        } catch (e: Exception) {
            // Silently catch any exceptions during destruction
            // This prevents crashes from window-related operations during app exit
        }
    }
}