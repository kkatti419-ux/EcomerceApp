package com.example.apiintegration.common.ui.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsPermissionScreen() {

    val permissionState = rememberPermissionState(
        Manifest.permission.READ_CONTACTS
    )

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        handlePermission(permissionState, context)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when {
            permissionState.status.isGranted -> {
                Text("Contacts Permission Granted ✅")
            }

            permissionState.status.shouldShowRationale -> {
                Text("Permission denied. Opening Settings...")
            }

            else -> {
                Text("Requesting Permission...")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            handlePermission(permissionState, context)
        }) {
            Text("Check Permission Again")
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun handlePermission(
    permissionState: PermissionState,
    context: Context
) {
    when {
        permissionState.status.isGranted -> {
            // Already granted
        }

        permissionState.status.shouldShowRationale -> {
            // User denied once → Go directly to settings
            openAppSettings(context)
        }

        else -> {
            // First time → Ask permission
            permissionState.launchPermissionRequest()
        }
    }
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}
