package com.example.nestednavigationsample.ui.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.nestednavigationsample.ui.settings.screen.about.AboutDetailScreen
import com.example.nestednavigationsample.ui.settings.screen.about.AboutMainScreen
import com.example.nestednavigationsample.ui.settings.screen.account.AccountDetailScreen
import com.example.nestednavigationsample.ui.settings.screen.account.AccountMainScreen
import com.example.nestednavigationsample.ui.settings.screen.general.GeneralDetailScreen
import com.example.nestednavigationsample.ui.settings.screen.general.GeneralMainScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

fun NavGraphBuilder.settingsGraph(navController: NavController) {

    val navigateToTab: (SettingsTab) -> Unit = { tab ->
        val isOnTab = navController.currentBackStackEntry?.destination?.hierarchy
            ?.any { it.route == tab.graphRoute } == true

        // If already on tab, pop to its root; else switch tab and restore state
        if (isOnTab) {
            navController.popBackStack(route = tab.mainRoute, inclusive = false)
        } else {
            navController.navigate(route = tab.graphRoute) {
                launchSingleTop = true
                restoreState = true
                popUpTo(SettingsRoutes.Graph) { saveState = true }
            }
        }
    }

    navigation(
        route = SettingsRoutes.Graph,
        startDestination = SettingsRoutes.GeneralGraph,
        deepLinks = listOf(
            navDeepLink { uriPattern = "app://nestednavigationsample/settings" }
        )
    ) {
        // General tab graph
        navigation(
            route = SettingsRoutes.GeneralGraph,
            startDestination = SettingsRoutes.GeneralMain
        ) {
            composable(
                route = SettingsRoutes.GeneralMain,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/general" }
                )
            ) { backStackEntry ->
                GeneralMainScreen(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() },
                    onGoDetails = { navController.navigate(SettingsRoutes.GeneralDetails) },
                )
            }
            composable(
                route = SettingsRoutes.GeneralDetails,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "app://nestednavigationsample/settings/general/details"
                    }
                )
            ) { backStackEntry ->
                GeneralDetailScreen(
                    selectedTab = SettingsTab.General,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // Account tab graph
        navigation(
            route = SettingsRoutes.AccountGraph,
            startDestination = SettingsRoutes.AccountMain
        ) {
            composable(
                route = SettingsRoutes.AccountMain,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/account" }
                )
            ) { backStackEntry ->
                val sharedViewModel = backStackEntry.sharedSettingsViewModel(navController)

                AccountMainScreen(
                    sharedViewModel = sharedViewModel,
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() },
                    onGoDetails = { navController.navigate(SettingsRoutes.AccountDetails) }
                )
            }
            composable(
                route = SettingsRoutes.AccountDetails,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "app://nestednavigationsample/settings/account/details"
                    }
                )
            ) { backStackEntry ->
                AccountDetailScreen(
                    selectedTab = SettingsTab.Account,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // About tab graph
        navigation(
            startDestination = SettingsRoutes.AboutMain,
            route = SettingsRoutes.AboutGraph
        ) {
            composable(
                route = SettingsRoutes.AboutMain,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "app://nestednavigationsample/settings/about" }
                )
            ) { backStackEntry ->
                val sharedViewModel = backStackEntry.sharedSettingsViewModel(navController)

                AboutMainScreen(
                    sharedViewModel = sharedViewModel,
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() },
                    onGoDetails = { navController.navigate(SettingsRoutes.AboutDetails) }
                )
            }
            composable(
                route = SettingsRoutes.AboutDetails,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "app://nestednavigationsample/settings/about/details"
                    }
                )
            ) { backStackEntry ->

                AboutDetailScreen(
                    selectedTab = SettingsTab.About,
                    onTabSelected = navigateToTab,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun NavBackStackEntry.sharedSettingsViewModel(navController: NavController): SettingsSharedViewModel {
    val parentEntry = remember(this) {
        navController.getBackStackEntry(SettingsRoutes.Graph)
    }
    return viewModel(parentEntry)
}