package com.example.nestednavhostsample.ui.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountScreen(onGoDetails: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Account")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onGoDetails) { Text("Go deeper") }
    }
}

@Composable
fun AccountDetailsScreen() {
    Text("Account • Details")
}

