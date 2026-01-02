package com.example.apiintegration.common.ui.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun VideoStoryCard(
    thumbnailRes: Int,
    videoUrl: String
) {
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 200.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        if (isPlaying) {
            VideoPlayer(
                videoUrl = videoUrl,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnailRes)
                    .size(400) // 👈 LIMIT SIZE (CRITICAL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { isPlaying = true }
            )

//            VideoThumbnail(
//                thumbnailRes = thumbnailRes,
//                modifier = Modifier.fillMaxSize(),
//                onClick = { isPlaying = true }
//            )
        }
    }
}
