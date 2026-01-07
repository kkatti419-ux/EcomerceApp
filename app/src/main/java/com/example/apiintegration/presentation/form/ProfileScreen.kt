package com.example.apiintegration.presentation.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.ui.theme.ApiIntegrationTheme

@Composable
fun ProfileScreenContent(
    firstname: String,
    lastname: String,
    place: String,
    age: String,
    selectedUserId: Int?,
    onFirstnameChange: (String) -> Unit,
    onLastnameChange: (String) -> Unit,
    onPlaceChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelEdit: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ProfileAvatar(imageUrl = null)

        AppOutlinedTextField(
            value = firstname, onValueChange = onFirstnameChange, label = "Firstname"
        )

        AppOutlinedTextField(
            singleLine = true,
            value = firstname, onValueChange = onFirstnameChange, label = "Firstname"
        )

        AppOutlinedTextField(
            value = firstname, onValueChange = onFirstnameChange, label = "Firstname"
        )

        AppOutlinedTextField(
            value = lastname, onValueChange = onLastnameChange, label = "Lastname"
        )

        AppOutlinedTextField(
            value = place, onValueChange = onPlaceChange, label = "Place"
        )

        AppOutlinedTextField(
            value = age, onValueChange = onAgeChange, label = "Age"
        )


        // 👇 This pushes content below to the bottom
        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(onClick = {}, text = "Save User")
    }


}

@Composable
fun ProfileScreen(
    viewModel: FormViewModel = hiltViewModel(),
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedUserId by remember { mutableStateOf<Int?>(null) }

    ProfileScreenContent(
        firstname = firstname,
        lastname = lastname,
        place = place,
        age = age,
        selectedUserId = selectedUserId,
        onFirstnameChange = { firstname = it },
        onLastnameChange = { lastname = it },
        onPlaceChange = { place = it },
        onAgeChange = { age = it },
        onSaveClick = {
            viewModel.upsertUser(
                firstname, lastname, selectedUserId, place, age
            )
        },
        onCancelEdit = {
            firstname = ""
            lastname = ""
            selectedUserId = null
        })
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ApiIntegrationTheme {
        ProfileScreenContent(
            firstname = "John",
            lastname = "Doe",
            place = "Bangalore",
            age = "26",
            selectedUserId = null,
            onFirstnameChange = {},
            onLastnameChange = {},
            onPlaceChange = {},
            onAgeChange = {},
            onSaveClick = {},
            onCancelEdit = {})
    }
}


@Composable
fun ProfileAvatar(
    imageUrl: String?,
    size: Dp = 100.dp,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build()
    )

    Image(
        painter = painterResource(
            R.drawable.cloth
        ),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.LightGray, CircleShape),
        contentScale = ContentScale.Crop
    )
}
