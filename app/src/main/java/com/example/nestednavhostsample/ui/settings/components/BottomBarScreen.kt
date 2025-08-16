package com.example.nestednavhostsample.ui.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nestednavhostsample.ui.settings.navigation.SettingsTab

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
                .padding(
                    top = 0.dp, // Skip top padding as we have our own TopAppBar in each screen
                    start = 0.dp, // Will be handled in each screen
                    end = 0.dp, // Will be handled in each screen

                    // The padding so that the content is not obscured by the bottom bar
                    bottom = innerPadding.calculateBottomPadding()
                ),
        ) { content() }
    }
}
