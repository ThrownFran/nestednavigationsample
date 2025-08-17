package com.example.nestednavigationsample.ui.settings.screen.general

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
fun GeneralMainScreen(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit,
    onBack: () -> Unit,
    onGoDetails: () -> Unit,
) {
    Scaffold(
        topBar = {
            SettingsTopBar("General Main", onBack)
        },
        bottomBar = {
            SettingsBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
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

@Preview
@Composable
private fun GeneralScreenPreview() {
    NestedNavigationSampleTheme {
        GeneralMainScreen(
            selectedTab = SettingsTab.General,
            onTabSelected = {},
            onBack = {},
            onGoDetails = {}
        )
    }
}
