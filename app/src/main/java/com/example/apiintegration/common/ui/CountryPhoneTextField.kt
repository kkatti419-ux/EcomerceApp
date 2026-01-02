package com.example.apiintegration.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apiintegration.data.remote.dto.Country

@Composable
fun CountryPhoneTextField(
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,

    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,

    countries: List<Country>,
    modifier: Modifier = Modifier
    ,

    textColor: Color = Color.Black,
    labelColor: Color = Color.Gray,

    focusedLabelColor: Color = Color.Black,
    focusedBorderColor: Color? = null,

    unfocusedBorderColor:Color? = null,
    cursorColor: Color = Color.Black,

    focusedContainerColor: Color = MaterialTheme.colorScheme.surface,
    unfocusedContainerColor: Color = MaterialTheme.colorScheme.surface,
    disabledContainerColor: Color? = null,
) {
    var expanded = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = onPhoneChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Phone Number") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            cursorColor = cursorColor,

            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = labelColor,

            focusedBorderColor = focusedBorderColor ?: Color.Transparent,
            unfocusedBorderColor = unfocusedBorderColor?:Color.Transparent,

            focusedContainerColor =focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor?:Color.Transparent
        ),

        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Country code selector
                Box {
                    Row(
                        modifier = Modifier
                            .clickable { expanded.value = true }
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        selectedCountry.phoneCode.isNotEmpty().let {
                            Text(text = selectedCountry.phoneCode)
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )


                    }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        countries.forEach { country ->
                            DropdownMenuItem(
                                text = {
                                    Text(country.name)
                                },
                                onClick = {
                                    onCountrySelected(country)
                                    expanded.value = false
                                }
                            )
                        }
                    }
                }

                // Vertical divider
                VerticalDivider(
                    modifier = Modifier
                        .height(24.dp)
                        .padding(horizontal = 4.dp)
                )
            }
        }


    )
}
