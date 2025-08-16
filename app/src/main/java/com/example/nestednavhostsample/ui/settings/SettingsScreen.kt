package com.example.nestednavhostsample.ui.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

object SettingsRoutes {
    const val Graph = "settings"
    const val GeneralGraph = "settings/general"
    const val AccountGraph = "settings/account"
    const val AboutGraph = "settings/about"

    const val GeneralRoot = "settings/general/root"
    const val GeneralDetail = "settings/general/detail"
    const val AccountRoot = "settings/account/root"
    const val AccountDetail = "settings/account/detail"
    const val AboutRoot = "settings/about/root"
    const val AboutDetail = "settings/about/detail"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsGeneralRootScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Settings - General")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate(SettingsRoutes.GeneralDetail) }) {
                Text("Go deeper")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsGeneralDetailScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Text("Settings - General (Detail)")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAccountRootScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Settings - Account")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate(SettingsRoutes.AccountDetail) }) {
                Text("Go deeper")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAccountDetailScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Text("Settings - Account (Detail)")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAboutRootScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Settings - About")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate(SettingsRoutes.AboutDetail) }) {
                Text("Go deeper")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAboutDetailScreen(navController: NavController, onExit: () -> Unit) {
    SettingsScaffold(
        navController = navController,
        title = "Settings",
        onExit = onExit
    ) {
        Text("Settings - About (Detail)")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScaffold(
    navController: NavController,
    title: String,
    onExit: () -> Unit,
    content: @Composable () -> Unit
) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentDestination = backStack?.destination

    // Intercept back inside Settings: if can pop within settings, do so; otherwise exit Settings
    BackHandler(enabled = navController.previousBackStackEntry != null) {
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = { TextButton(onClick = onExit) { Text("Back") } }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination.isInGraph(SettingsRoutes.GeneralGraph),
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
                    selected = currentDestination.isInGraph(SettingsRoutes.AccountGraph),
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
                    selected = currentDestination.isInGraph(SettingsRoutes.AboutGraph),
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

private fun NavDestination?.isInGraph(graphRoute: String): Boolean =
    this?.hierarchy?.any { it.route == graphRoute } == true
