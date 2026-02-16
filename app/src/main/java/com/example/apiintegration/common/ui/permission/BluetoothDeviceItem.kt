package com.example.apiintegration.common.ui.permission

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class BluetoothDeviceItem(
    val name: String?,
    val address: String
)


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BluetoothScanScreen() {

    val context = LocalContext.current
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    var devices by remember { mutableStateOf(listOf<BluetoothDeviceItem>()) }
    var showButtons by remember { mutableStateOf(true) }   // ✅ Toggle default TRUE

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        Log.d("BT_DEBUG", "Permission result: $permissions")

        val granted = permissions.values.all { it }

        if (granted) {
            Log.d("BT_DEBUG", "All permissions granted")
            startBluetoothScan(context, bluetoothAdapter) {
                devices = it
                Log.d("BT_DEBUG", "Devices Updated: $it")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ✅ TOGGLE BUTTON
        Button(onClick = {
            showButtons = !showButtons
            Log.d("BT_DEBUG", "Toggle Clicked → showButtons = $showButtons")
        }) {
            Text(if (showButtons) "Hide Buttons" else "Show Buttons")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (showButtons) {

            Button(onClick = {
                Log.d("BT_DEBUG", "Scan Button Clicked")
                launcher.launch(
                    arrayOf(
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.BLUETOOTH_CONNECT,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }) {
                Text("Scan Bluetooth Devices 🔍")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(devices) { device ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(text = device.name ?: "Unknown Device")
                            Text(text = device.address)

                            // ✅ Share when tapping device name
                            Spacer(modifier = Modifier.height(6.dp))

                            Button(onClick = {
                                Log.d("BT_DEBUG", "Share clicked for ${device.name}")
                                shareTextToBluetooth(context, device)
                            }) {
                                Text("Share")
                            }
                        }

                        // ✅ CONNECT BUTTON
                        Button(
                            onClick = {
                                Log.d("BT_DEBUG", "Connect clicked for ${device.name}")
                                pairDevice(bluetoothAdapter, device.address)
                            }
                        ) {
                            Text("Connect")
                        }
                    }
                }
            }
        }
    }
}


@SuppressLint("MissingPermission")
fun pairDevice(adapter: BluetoothAdapter, address: String) {

    val device = adapter.getRemoteDevice(address)

    try {
        Log.d("BT_DEBUG", "Trying to pair with $address")

        val method = device.javaClass.getMethod("createBond")
        method.invoke(device)

        Log.d("BT_DEBUG", "Pairing Started")

    } catch (e: Exception) {
        Log.e("BT_DEBUG", "Pairing Failed: ${e.message}")
    }
}


fun shareTextToBluetooth(context: Context, device: BluetoothDeviceItem) {

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Hello from my App 🚀")
        type = "text/plain"
        setPackage("com.android.bluetooth")
    }

    try {
        context.startActivity(
            Intent.createChooser(sendIntent, "Share to ${device.name}")
        )
        Log.d("BT_DEBUG", "Share Intent Launched")
    } catch (e: Exception) {
        Log.e("BT_DEBUG", "Share Failed: ${e.message}")
    }
}


@SuppressLint("MissingPermission")
fun startBluetoothScan(
    context: Context,
    adapter: BluetoothAdapter,
    onResult: (List<BluetoothDeviceItem>) -> Unit
) {

    Log.d("BT_DEBUG", "Starting Bluetooth Discovery")

    val deviceList = mutableListOf<BluetoothDeviceItem>()

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if (BluetoothDevice.ACTION_FOUND == intent.action) {

                val device: BluetoothDevice? =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)

                device?.let {

                    Log.d("BT_DEBUG", "Device Found: ${it.name}")

                    deviceList.add(
                        BluetoothDeviceItem(it.name, it.address)
                    )
                    onResult(deviceList)
                }
            }
        }
    }

    val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
    context.registerReceiver(receiver, filter)

    adapter.startDiscovery()
}

