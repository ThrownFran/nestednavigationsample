package com.example.nestednavhostsample.ui.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nestednavhostsample.ui.settings.components.SettingsScaffold
import com.example.nestednavhostsample.ui.settings.components.SettingsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralScreen(
    onBack: () -> Unit,
    onGoDetails: () -> Unit,
) {
    SettingsScaffold(
        topBar = { SettingsTopBar(onBack) },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Button(
                onClick = onGoDetails
            ) {
                Text("Go deeper")
            }

            Spacer(Modifier.height(16.dp))

            repeat(30) {
                Text("Item should no be obscured by BottomBar$it")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralDetailsScreen(onBack: () -> Unit) {
    SettingsScaffold(
        topBar = {
            SettingsTopBar(onBack)
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(innerPadding),
        ) {
            Text("General • Details")
        }
    }
}
