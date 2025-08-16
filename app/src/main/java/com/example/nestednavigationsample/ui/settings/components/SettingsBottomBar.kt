package com.example.nestednavigationsample.ui.settings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.nestednavigationsample.ui.settings.navigation.SettingsTab

@Composable
fun SettingsBottomBar(
    selectedTab: SettingsTab,
    onTabSelected: (SettingsTab) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == SettingsTab.General,
            onClick = { onTabSelected(SettingsTab.General) },
            label = { Text("General") },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "General") }
        )
        NavigationBarItem(
            selected = selectedTab == SettingsTab.Account,
            onClick = { onTabSelected(SettingsTab.Account) },
            label = { Text("Account") },
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") }
        )
        NavigationBarItem(
            selected = selectedTab == SettingsTab.About,
            onClick = { onTabSelected(SettingsTab.About) },
            label = { Text("About") },
            icon = { Icon(Icons.Filled.Info, contentDescription = "About") }
        )
    }
}
