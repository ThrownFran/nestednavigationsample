package com.example.nestednavigationsample.ui.settings.navigation

object SettingsRoutes {
    // Parent graph
    const val Graph = "settings"

    // Tab graphs (distinct from destinations for clarity)
    const val GeneralGraph = "settings/general_graph"
    const val AccountGraph = "settings/account_graph"
    const val AboutGraph = "settings/about_graph"

    // Destinations
    const val GeneralMain = "settings/general"
    const val GeneralDetails = "settings/general/details"

    const val AccountMain = "settings/account"
    const val AccountDetails = "settings/account/details"

    const val AboutMain = "settings/about"
    const val AboutDetails = "settings/about/details"
}

enum class SettingsTab(val graphRoute: String, val mainRoute: String) {
    General(SettingsRoutes.GeneralGraph, SettingsRoutes.GeneralMain),
    Account(SettingsRoutes.AccountGraph, SettingsRoutes.AccountMain),
    About(SettingsRoutes.AboutGraph, SettingsRoutes.AboutMain),
}
