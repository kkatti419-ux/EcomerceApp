package com.example.apiintegration.common.ui.video

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun YoutubeStoryCard(
    thumbnailRes: Int,
    youtubeUrl: String,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 200.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    youtubeUrl.toUri()
                )
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(thumbnailRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // ▶ Play icon
        Box(
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.Center)
                .background(Color.Black.copy(alpha = 0.6f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
