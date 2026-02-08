package com.example.apiintegration.presentation.androidIntents

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun ShareTextScreen(navController: NavController) {

    // Get Android context inside Compose
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val textToShare = "Hello 👋 This text can be copied and shared!"

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, textToShare)
                }

                context.startActivity(
                    Intent.createChooser(intent, "Share via")
                )
            }
        ) {
            Text(text = "Share Text")
        }
    }
}
