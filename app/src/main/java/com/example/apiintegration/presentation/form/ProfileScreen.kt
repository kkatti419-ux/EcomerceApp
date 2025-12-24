package com.example.apiintegration.presentation.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.presentation.form.FormViewModel

@Composable
fun ProfileScreen(
    viewModel: FormViewModel = hiltViewModel()
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    
    // State to track if we are editing a user
    var selectedUserId by remember { mutableStateOf<Int?>(null) }

    val users by viewModel.users.collectAsState(initial = emptyList())


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppOutlinedTextField(
                value = firstname,
                onValueChange = { firstname = it },
                label = "Firstname"
            )

            AppOutlinedTextField(
                value = lastname,
                onValueChange = { lastname = it },
                label = "Lastname"
            )

            Spacer(modifier = Modifier.padding(12.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                 PrimaryButton(
                    onClick = {
                        if (firstname.isNotBlank() && lastname.isNotBlank()) {
                            viewModel.upsertUser(firstname, lastname, selectedUserId)
                            // clear fields
                            firstname = ""
                            lastname = ""
                            selectedUserId = null
                        }
                    },
                    text = if (selectedUserId == null) "Save User" else "Update User"
                )
                
                if (selectedUserId != null) {
                    Button(
                        onClick = {
                            firstname = ""
                            lastname = ""
                            selectedUserId = null
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Cancel Edit")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            
            // List of Users
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    UserItem(
                        user = user,
                        onDeleteClick = { viewModel.deleteUser(user) },
                        onEditClick = {
                            firstname = user.firstName
                            lastname = user.lastName
                            selectedUserId = user.id
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UserItem(
    user: UserProfile,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Row {
                 IconButton(onClick = onEditClick) {
                    Icon(
                         imageVector = Icons.Default.Edit,
                         contentDescription = "Edit"
                    )
                }
                
                IconButton(onClick = onDeleteClick) {
                    Icon(
                         imageVector = Icons.Default.Delete,
                         contentDescription = "Delete",
                         tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}