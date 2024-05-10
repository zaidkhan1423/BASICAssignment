package com.zaid.basicassignment.core.presentation.navigation

sealed class Screen(val route: String) {

    data object HomeScreen: Screen("home_screen")
    data object ShortsScreen: Screen("shorts_screen")
    data object CreateScreen: Screen("create_screen")
    data object SubscriptionsScreen: Screen("subscriptions_screen")
    data object ProfileScreen: Screen("profile_screen")

    data object SearchScreen: Screen("search_screen")

}