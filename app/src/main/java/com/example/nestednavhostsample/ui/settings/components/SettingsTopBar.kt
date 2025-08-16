package com.example.nestednavhostsample.ui.settings.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text("Settings") },
        navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }
    )
}

