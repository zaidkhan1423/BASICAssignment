package com.zaid.basicassignment.core.navigation.nav_graphs

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.basicassignment.core.navigation.NavGraphRoutes
import com.zaid.basicassignment.core.navigation.sharedViewModel
import com.zaid.basicassignment.core.presentation.navigation.Screen
import com.zaid.basicassignment.feature_create_video.CreateScreen
import com.zaid.basicassignment.feature_home.presentation.home_screen.HomeScreen
import com.zaid.basicassignment.feature_home.presentation.home_screen.HomeScreenViewModel
import com.zaid.basicassignment.feature_home.presentation.search_screen.SearchScreen
import com.zaid.basicassignment.feature_profile.ProfileScreen
import com.zaid.basicassignment.feature_shorts.ShortsScreen
import com.zaid.basicassignment.feature_subscriptions.SubscriptionsScreen

fun NavGraphBuilder.topLevelGraph(
    navController: NavHostController,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {
    navigation(startDestination = Screen.HomeScreen.route, route = NavGraphRoutes.TOP_LEVEL) {
        composable(route = Screen.HomeScreen.route) { entry ->

            val homeScreenViewModel: HomeScreenViewModel =
                entry.sharedViewModel(navHostController = navController)
            val homeScreenUiState by homeScreenViewModel.homeUiState.collectAsStateWithLifecycle()

            HomeScreen(
                navController = navController,
                uiState = homeScreenUiState,
                onShowSnackBar = onShowSnackBar
            )
        }
        composable(route = Screen.ShortsScreen.route) {
            ShortsScreen()
        }
        composable(route = Screen.CreateScreen.route) {
            CreateScreen()
        }
        composable(route = Screen.SubscriptionsScreen.route) {
            SubscriptionsScreen()
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(route = Screen.SearchScreen.route) { entry ->
            val homeScreenViewModel: HomeScreenViewModel =
                entry.sharedViewModel(navHostController = navController)
            val homeScreenUiState by homeScreenViewModel.homeUiState.collectAsStateWithLifecycle()
            SearchScreen(
                navController = navController,
                uiState = homeScreenUiState,
                onShowSnackBar = onShowSnackBar
            )
        }

    }
}