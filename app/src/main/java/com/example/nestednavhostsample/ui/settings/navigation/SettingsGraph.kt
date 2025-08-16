package com.example.nestednavhostsample.ui.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.nestednavhostsample.ui.settings.components.SettingsScaffold
import com.example.nestednavhostsample.ui.settings.screens.AboutDetailsScreen
import com.example.nestednavhostsample.ui.settings.screens.AboutScreen
import com.example.nestednavhostsample.ui.settings.screens.AccountDetailsScreen
import com.example.nestednavhostsample.ui.settings.screens.AccountScreen
import com.example.nestednavhostsample.ui.settings.screens.GeneralDetailsScreen
import com.example.nestednavhostsample.ui.settings.screens.GeneralScreen

fun NavGraphBuilder.settingsGraph(navController: NavController) {

    val navigateToTab: (SettingsTab) -> Unit = { tab ->
        val target = when (tab) {
            SettingsTab.General -> SettingsRoutes.GeneralGraph
            SettingsTab.Account -> SettingsRoutes.AccountGraph
            SettingsTab.About -> SettingsRoutes.AboutGraph
        }
        navController.navigate(target) {
            launchSingleTop = true
            restoreState = true
            popUpTo(SettingsRoutes.Graph) { saveState = true }
        }
    }

    navigation(
        startDestination = SettingsRoutes.GeneralGraph,
        route = SettingsRoutes.Graph,
        deepLinks = listOf(
            navDeepLink { uriPattern = "app://nestednavhostsample/settings" }
        )
    ) {
        // General tab graph
        navigation(
            startDestination = SettingsRoutes.General,
            route = SettingsRoutes.GeneralGraph
        ) {
            composable(
                route = SettingsRoutes.General,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/general" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    GeneralScreen(onGoDetails = { navController.navigate(SettingsRoutes.GeneralDetails) })
                }
            }
            composable(
                route = SettingsRoutes.GeneralDetails,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/general/details" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    GeneralDetailsScreen()
                }
            }
        }

        // Account tab graph
        navigation(
            startDestination = SettingsRoutes.Account,
            route = SettingsRoutes.AccountGraph
        ) {
            composable(
                route = SettingsRoutes.Account,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/account" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    AccountScreen(onGoDetails = { navController.navigate(SettingsRoutes.AccountDetails) })
                }
            }
            composable(
                route = SettingsRoutes.AccountDetails,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/account/details" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    AccountDetailsScreen()
                }
            }
        }

        // About tab graph
        navigation(
            startDestination = SettingsRoutes.About,
            route = SettingsRoutes.AboutGraph
        ) {
            composable(
                route = SettingsRoutes.About,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/about" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    AboutScreen(onGoDetails = { navController.navigate(SettingsRoutes.AboutDetails) })
                }
            }
            composable(
                route = SettingsRoutes.AboutDetails,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavhostsample/settings/about/details" }
                )
            ) {
                SettingsScaffold(
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                ) {
                    AboutDetailsScreen()
                }
            }
        }
    }
}
