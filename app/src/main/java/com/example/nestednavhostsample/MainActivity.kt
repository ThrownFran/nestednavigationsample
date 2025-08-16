package com.example.nestednavhostsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nestednavhostsample.ui.home.HomeScreen
import com.example.nestednavhostsample.ui.search.SearchScreen
import com.example.nestednavhostsample.ui.settings.SettingsAboutDetailScreen
import com.example.nestednavhostsample.ui.settings.SettingsAboutRootScreen
import com.example.nestednavhostsample.ui.settings.SettingsAccountDetailScreen
import com.example.nestednavhostsample.ui.settings.SettingsAccountRootScreen
import com.example.nestednavhostsample.ui.settings.SettingsGeneralDetailScreen
import com.example.nestednavhostsample.ui.settings.SettingsGeneralRootScreen
import com.example.nestednavhostsample.ui.settings.SettingsRoutes
import com.example.nestednavhostsample.ui.theme.NestedNavHostSampleTheme
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedNavHostSampleTheme {
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}

private object Routes {
    const val Home = "home"
    const val Search = "search"
    const val Settings = SettingsRoutes.Graph
}

@Composable
private fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Home) {
            HomeScreen(
                onSearchClick = { navController.navigate(Routes.Search) },
                onSettingsClick = {
                    navController.navigate(Routes.Settings) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    }
                }
            )
        }
        composable(Routes.Search) {
            SearchScreen(onBack = { navController.popBackStack() })
        }

        // Settings graph with nested tab graphs and deep links on inner destinations
        navigation(
            startDestination = SettingsRoutes.GeneralGraph,
            route = SettingsRoutes.Graph,
            deepLinks = listOf(
                // Optional graph-level deep link to open Settings (defaults to General tab)
                navDeepLink { uriPattern = "app://nestednavhostsample/settings" }
            )
        ) {
            // General
            navigation(
                startDestination = SettingsRoutes.GeneralRoot,
                route = SettingsRoutes.GeneralGraph
            ) {
                composable(
                    route = SettingsRoutes.GeneralRoot,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/general/root" }
                    )
                ) {
                    SettingsGeneralRootScreen(navController, onExit = { navController.popBackStack() })
                }
                composable(
                    route = SettingsRoutes.GeneralDetail,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/general/detail" }
                    )
                ) {
                    SettingsGeneralDetailScreen(navController, onExit = { navController.popBackStack() })
                }
            }

            // Account
            navigation(
                startDestination = SettingsRoutes.AccountRoot,
                route = SettingsRoutes.AccountGraph
            ) {
                composable(
                    route = SettingsRoutes.AccountRoot,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/account/root" }
                    )
                ) {
                    SettingsAccountRootScreen(navController, onExit = { navController.popBackStack() })
                }
                composable(
                    route = SettingsRoutes.AccountDetail,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/account/detail" }
                    )
                ) {
                    SettingsAccountDetailScreen(navController, onExit = { navController.popBackStack() })
                }
            }

            // About
            navigation(
                startDestination = SettingsRoutes.AboutRoot,
                route = SettingsRoutes.AboutGraph
            ) {
                composable(
                    route = SettingsRoutes.AboutRoot,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/about/root" }
                    )
                ) {
                    SettingsAboutRootScreen(navController, onExit = { navController.popBackStack() })
                }
                composable(
                    route = SettingsRoutes.AboutDetail,
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://nestednavhostsample/settings/about/detail" }
                    )
                ) {
                    SettingsAboutDetailScreen(navController, onExit = { navController.popBackStack() })
                }
            }
        }
    }
}
