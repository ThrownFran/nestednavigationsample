package com.example.nestednavigationsample.ui.settings.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable

@Composable
fun SettingsScaffold(
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { topBar() },
        // Exclude the navigation bars because they are already handled by the Outer Scaffold.
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(
            WindowInsets.navigationBars
        )
    ) { innerPadding ->
        content(innerPadding)
    }
}
