package com.zaid.basicassignment.core.presentation.my_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zaid.basicassignment.core.presentation.navigation.Screen
import com.zaid.basicassignment.core.presentation.navigation.TopLevelDestination

@Composable
fun rememberMyAppState(
    navController: NavHostController = rememberNavController(),
): MyAppState {
    return remember(
        navController
    ) {
        MyAppState(
            navController
        )
    }
}

class MyAppState(
    val navController: NavHostController,
    ) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val shouldShowBottomBar: Boolean
        @Composable get() = when(currentDestination?.route) {
            Screen.HomeScreen.route -> true
            Screen.ShortsScreen.route -> true
            Screen.CreateScreen.route -> true
            Screen.SubscriptionsScreen.route -> true
            Screen.ProfileScreen.route -> true
            else -> false
        }

}