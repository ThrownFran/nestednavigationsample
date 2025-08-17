package com.example.nestednavigationsample.ui.settings.screen.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nestednavigationsample.ui.settings.components.SettingsBottomBar
import com.example.nestednavigationsample.ui.settings.components.SettingsTopBar
import com.example.nestednavigationsample.ui.settings.navigation.SettingsTab
import com.example.nestednavigationsample.ui.theme.NestedNavigationSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit,
    onBack: () -> Unit
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize(),
        ) {
            Text("Account • Details")
        }
    }
}

@Preview
@Composable
private fun AccountDetailScreenPreview() {
    NestedNavigationSampleTheme {
        AccountDetailScreen(
            selectedTab = SettingsTab.Account,
            onTabSelected = {},
            onBack = {}
        )
    }
}