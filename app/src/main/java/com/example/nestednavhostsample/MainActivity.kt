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
import com.example.nestednavhostsample.ui.settings.navigation.SettingsRoutes
import com.example.nestednavhostsample.ui.settings.navigation.settingsGraph
import com.example.nestednavhostsample.ui.theme.NestedNavHostSampleTheme
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
        // Settings graph
        settingsGraph(navController)
    }
}
