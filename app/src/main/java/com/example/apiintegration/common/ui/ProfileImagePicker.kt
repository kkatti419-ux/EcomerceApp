package com.example.apiintegration.common.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.apiintegration.R

@Composable
fun ProfileImagePicker(
    imageUri: Uri?,
    onImagePicked: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let(onImagePicked)
    }

    Image(
        painter = if (imageUri == null)
            painterResource(R.drawable.camera_with_circle)
        else
            rememberAsyncImagePainter(imageUri),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop, // 🔑 FIX
        modifier = modifier
            .size(90.dp)                 // 🔑 ensure square
            .clip(CircleShape)
            .clickable {
                launcher.launch("image/*")
            }
    )
}
