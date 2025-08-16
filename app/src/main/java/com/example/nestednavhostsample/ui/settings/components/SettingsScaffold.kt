package com.example.nestednavhostsample.ui.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.nestednavhostsample.ui.settings.navigation.SettingsTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScaffold(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { SettingsTopBar(onBack) },
        bottomBar = { SettingsBottomBar(selectedTab = selectedTab, onTabSelected = onTabSelected) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) { content() }
    }
}
