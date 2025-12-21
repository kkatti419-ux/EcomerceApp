package com.example.apiintegration.presentation.gemini

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// GeminiScreen.kt
// This file contains the UI components using Jetpack Compose.
// It follows the Unidirectional Data Flow (UDF) pattern:
// 1. UI observes state from ViewModel.
// 2. UI renders based on state.
// 3. User interactions trigger events in ViewModel.

@Composable
fun GeminiScreen(
    viewModel: GeminiViewModel = viewModel()
) {
    // Collect the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()
    
    // Local state for the text input
    var prompt by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Gemini AI",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Input Area
        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text("Ask Gemini something...") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Button(
            onClick = { viewModel.sendPrompt(prompt) },
            modifier = Modifier.align(Alignment.End),
            enabled = uiState !is GeminiUiState.Loading
        ) {
            Text("Generate")
        }

        // Result Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            when (val state = uiState) {
                is GeminiUiState.Idle -> {
                    Text(
                        text = "Enter a prompt to get started.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is GeminiUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is GeminiUiState.Success -> {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = "Response:",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.response,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                is GeminiUiState.Error -> {
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
