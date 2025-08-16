package com.example.nestednavhostsample.ui.settings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nestednavhostsample.ui.settings.navigation.SettingsRoutes

@Composable
fun SettingsBottomBar(navController: NavController) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute?.startsWith(SettingsRoutes.GeneralGraph) == true
                    || currentRoute == SettingsRoutes.General
                    || currentRoute == SettingsRoutes.GeneralDetails,
            onClick = {
                navController.navigate(SettingsRoutes.GeneralGraph) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(SettingsRoutes.Graph) { saveState = true }
                }
            },
            label = { Text("General") },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "General") }
        )
        NavigationBarItem(
            selected = currentRoute?.startsWith(SettingsRoutes.AccountGraph) == true
                    || currentRoute == SettingsRoutes.Account
                    || currentRoute == SettingsRoutes.AccountDetails,
            onClick = {
                navController.navigate(SettingsRoutes.AccountGraph) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(SettingsRoutes.Graph) { saveState = true }
                }
            },
            label = { Text("Account") },
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") }
        )
        NavigationBarItem(
            selected = currentRoute?.startsWith(SettingsRoutes.AboutGraph) == true
                    || currentRoute == SettingsRoutes.About
                    || currentRoute == SettingsRoutes.AboutDetails,
            onClick = {
                navController.navigate(SettingsRoutes.AboutGraph) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(SettingsRoutes.Graph) { saveState = true }
                }
            },
            label = { Text("About") },
            icon = { Icon(Icons.Filled.Info, contentDescription = "About") }
        )
    }
}

