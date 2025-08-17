package com.example.nestednavigationsample.ui.settings.screen.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nestednavigationsample.ui.settings.components.SettingsBottomBar
import com.example.nestednavigationsample.ui.settings.components.SettingsTopBar
import com.example.nestednavigationsample.ui.settings.navigation.SettingsTab
import com.example.nestednavigationsample.ui.theme.NestedNavigationSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutMainScreen(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit,
    onBack: () -> Unit,
    onGoDetails: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsTopBar(onBack)
        },
        bottomBar = {
            SettingsBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("About")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onGoDetails) { Text("Go deeper") }
        }
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    NestedNavigationSampleTheme {
        AboutMainScreen(
            selectedTab = SettingsTab.About,
            onTabSelected = {},
            onBack = {},
            onGoDetails = {}
        )
    }
}