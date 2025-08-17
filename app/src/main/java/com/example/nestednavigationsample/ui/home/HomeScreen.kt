package com.example.nestednavigationsample.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nestednavigationsample.ui.theme.NestedNavigationSampleTheme

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Home")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onSearchClick) { Text("Go to Search") }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onSettingsClick) { Text("Go to Settings") }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NestedNavigationSampleTheme {
        HomeScreen(
            onSearchClick = {},
            onSettingsClick = {}
        )
    }
}