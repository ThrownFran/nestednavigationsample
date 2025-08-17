package com.example.nestednavigationsample.ui.settings.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Shared ViewModel for all Settings tabs, scoped to SettingsRoutes.Graph.
 * It lives as long as the settings graph back stack entry lives.
 */
class SettingsSharedViewModel : ViewModel() {
    var sampleCounter by mutableStateOf(0)
        private set

    fun increment() {
        sampleCounter++
    }
}

