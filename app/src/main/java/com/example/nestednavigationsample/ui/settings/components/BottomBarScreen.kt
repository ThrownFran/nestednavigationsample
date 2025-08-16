package com.example.nestednavigationsample.ui.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nestednavigationsample.ui.settings.navigation.SettingsTab

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BottomBarScreen(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            SettingsBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        ) { content() }
    }
}
