package com.example.nestednavigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nestednavigationsample.ui.home.HomeScreen
import com.example.nestednavigationsample.ui.search.SearchScreen
import com.example.nestednavigationsample.ui.settings.navigation.SettingsRoutes
import com.example.nestednavigationsample.ui.settings.navigation.settingsGraph
import com.example.nestednavigationsample.ui.theme.NestedNavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedNavigationSampleTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
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
                onSearchClick = {
                    navController.navigate(Routes.Search)
                },
                onSettingsClick = {
                    navController.navigate(Routes.Settings)
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
