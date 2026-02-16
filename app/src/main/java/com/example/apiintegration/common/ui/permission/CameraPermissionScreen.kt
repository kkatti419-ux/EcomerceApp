package com.example.apiintegration.common.ui.permission

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionScreen() {

    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )

    var showExplanationDialog by remember { mutableStateOf(true) }

    // 🔹 Custom explanation dialog BEFORE system popup
    if (showExplanationDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Camera Access Required 📸")
            },
            text = {
                Text(
                    text = "We use your camera to capture profile photos securely. " +
                            "Your data is not stored without your permission."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showExplanationDialog = false
                        cameraPermissionState.launchPermissionRequest()
                    }
                ) {
                    Text("Continue")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showExplanationDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when {
            cameraPermissionState.status.isGranted -> {
                Text("Camera Permission Granted ✅")
            }

            cameraPermissionState.status.shouldShowRationale -> {
                Text("Camera access is required to take pictures.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }) {
                    Text("Grant Permission")
                }
            }

            else -> {
                Button(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }) {
                    Text("Request Camera Permission")
                }
            }
        }
    }
}
