package com.example.apiintegration.common.ui.permission


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun WhatsAppScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                openWhatsApp(context, "919108724443") // 🔥 Replace with your number
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chat on WhatsApp 💬")
        }
    }
}



fun openWhatsApp(context: Context, phoneNumber: String) {

    val message = "Hello, I want to connect with you."
    val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = url.toUri()
        setPackage("com.whatsapp")
    }

    try {
        context.startActivity(intent)

    } catch (e: ActivityNotFoundException) {

        // WhatsApp not installed → Open Play Store
        try {
            val playStoreIntent = Intent(
                Intent.ACTION_VIEW,
                "market://details?id=com.whatsapp".toUri()
            )
            context.startActivity(playStoreIntent)

        } catch (ex: ActivityNotFoundException) {

            // If Play Store app not available → Open browser
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                "https://play.google.com/store/apps/details?id=com.whatsapp".toUri()
            )
            context.startActivity(browserIntent)
        }
    }
}
