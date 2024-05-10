package com.zaid.basicassignment.core.presentation.navigation

import com.zaid.basicassignment.core.utils.AppIcons
import com.zaid.basicassignment.core.utils.Icon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val route: String
) {

    HOME_SCREEN(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeSelectedIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeUnselectedIcon),
        route = Screen.HomeScreen.route
    ),
    SHORTS_SCREEN(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.ShortsSelectedIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.ShortsUnselectedIcon),
        route = Screen.ShortsScreen.route
    ),
    CREATE_SCREEN(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.CreateSelectedIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.CreateUnselectedIcon),
        route = Screen.CreateScreen.route
    ),
    SUBSCRIPTIONS_SCREEN(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.SubscriptionsSelectedIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.SubscriptionsUnselectedIcon),
        route = Screen.SubscriptionsScreen.route
    ),
    PROFILE_SCREEN(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileSelectedIcon),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileUnselectedIcon),
        route = Screen.ProfileScreen.route
    )

}