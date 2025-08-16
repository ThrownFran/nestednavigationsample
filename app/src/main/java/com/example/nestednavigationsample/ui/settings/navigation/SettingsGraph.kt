package com.example.nestednavigationsample.ui.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.nestednavigationsample.ui.settings.components.BottomBarScreen
import com.example.nestednavigationsample.ui.settings.screens.AboutDetailsScreen
import com.example.nestednavigationsample.ui.settings.screens.AboutScreen
import com.example.nestednavigationsample.ui.settings.screens.AccountDetailsScreen
import com.example.nestednavigationsample.ui.settings.screens.AccountScreen
import com.example.nestednavigationsample.ui.settings.screens.GeneralDetailsScreen
import com.example.nestednavigationsample.ui.settings.screens.GeneralScreen

fun NavGraphBuilder.settingsGraph(navController: NavController) {

    val navigateToTab: (SettingsTab) -> Unit = { tab ->
        val isOnTab = navController.currentBackStackEntry?.destination?.hierarchy
            ?.any { it.route == tab.graphRoute } == true

        // If already on tab, pop to its root; else switch tab and restore state
        if (isOnTab) {
            navController.popBackStack(tab.rootRoute, inclusive = false, saveState = false)
        } else {
            navController.navigate(tab.graphRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(SettingsRoutes.Graph) { saveState = true }
            }
        }
    }

    navigation(
        startDestination = SettingsRoutes.GeneralGraph,
        route = SettingsRoutes.Graph,
        deepLinks = listOf(
            navDeepLink { uriPattern = "app://nestednavigationsample/settings" }
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
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/general" }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab
                ) {
                    GeneralScreen(
                        onBack = { navController.popBackStack() },
                        onGoDetails = { navController.navigate(SettingsRoutes.GeneralDetails) },
                    )
                }
            }
            composable(
                route = SettingsRoutes.GeneralDetails,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "app://nestednavigationsample/settings/general/details"
                    }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab
                ) {
                    GeneralDetailsScreen(onBack = { navController.popBackStack() })
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
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/account" }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab
                ) {
                    AccountScreen(
                        onBack = { navController.popBackStack() },
                        onGoDetails = { navController.navigate(SettingsRoutes.AccountDetails) }
                    )
                }
            }
            composable(
                route = SettingsRoutes.AccountDetails,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "app://nestednavigationsample/settings/account/details"
                    }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab
                ) {
                    AccountDetailsScreen(onBack = { navController.popBackStack() })
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
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/about" }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab
                ) {
                    AboutScreen(
                        onBack = { navController.popBackStack() },
                        onGoDetails = { navController.navigate(SettingsRoutes.AboutDetails) }
                    )
                }
            }
            composable(
                route = SettingsRoutes.AboutDetails,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/about/details" }
                )
            ) {
                BottomBarScreen(
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab
                ) {
                    AboutDetailsScreen(onBack = { navController.popBackStack() })
                }
            }
        }
    }
}
