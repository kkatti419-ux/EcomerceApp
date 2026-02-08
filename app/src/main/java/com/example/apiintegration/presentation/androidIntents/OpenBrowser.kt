package com.example.apiintegration.presentation.androidIntents


import android.content.Intent
import android.net.Uri
 import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavController


@Composable
fun OpenBrowserScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "https://google.com".toUri()
                )
                context.startActivity(intent)
            }
        ) {
            Text(text = "Open Browser")
        }
    }
}
