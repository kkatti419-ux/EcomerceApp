package com.example.apiintegration.common.ui.permission


import android.Manifest
import android.content.Intent
import android.provider.ContactsContract
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

data class Contact(
    val name: String,
    val phone: String
)

//@Composable
//fun ContactsListScreen() {
//
//    val context = LocalContext.current
//    var contacts by remember { mutableStateOf(listOf<Contact>()) }
//
//    LaunchedEffect(Unit) {
//        contacts = fetchContacts(context)
//    }
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize().padding(16.dp)
//    ) {
//        items(contacts) { contact ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 6.dp)
//                    .clickable {
//                        dialNumber(context, contact.phone)
//                    }
//            ) {
//                Column(modifier = Modifier.padding(12.dp)) {
//                    Text(text = contact.name)
//                    Text(text = contact.phone)
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactsListScreen() {

    val context = LocalContext.current
    val permissionState = rememberPermissionState(
        Manifest.permission.READ_CONTACTS
    )

    var contacts by remember { mutableStateOf(listOf<Contact>()) }

    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
            contacts = fetchContacts(context)
        }
    }

    when {
        permissionState.status.isGranted -> {
            LazyColumn {
                items(contacts) { contact ->
                    Text("${contact.name} - ${contact.phone}")
                }
            }
        }

        else -> {
            Button(onClick = {
                permissionState.launchPermissionRequest()
            }) {
                Text("Grant Contacts Permission")
            }
        }
    }
}


fun fetchContacts(context: android.content.Context): List<Contact> {

    val contactList = mutableListOf<Contact>()

    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
    )

    cursor?.use {
        while (it.moveToNext()) {

            val name = it.getString(
                it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )
            )

            val phone = it.getString(
                it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
            )

            contactList.add(Contact(name, phone))
        }
    }

    return contactList
}



fun dialNumber(context: android.content.Context, phone: String) {

    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phone")
    }

    context.startActivity(intent)
}
