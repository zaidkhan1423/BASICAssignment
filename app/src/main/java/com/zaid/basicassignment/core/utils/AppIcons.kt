package com.zaid.basicassignment.core.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.zaid.basicassignment.R

object AppIcons {
    val HomeSelectedIcon = R.drawable.home_selected
    val HomeUnselectedIcon = R.drawable.home_unselected
    val ShortsSelectedIcon = R.drawable.shorts_selected
    val ShortsUnselectedIcon = R.drawable.shorts_unselected
    val CreateSelectedIcon = R.drawable.create_selected
    val CreateUnselectedIcon = R.drawable.create_unselected
    val SubscriptionsSelectedIcon = R.drawable.subscription_selected
    val SubscriptionsUnselectedIcon = R.drawable.subscription_unselected
    val ProfileSelectedIcon = R.drawable.profile_selected
    val ProfileUnselectedIcon = R.drawable.profile_unselected
    val AppLogoLight = R.drawable.logo_light
    val AppLogoDark = R.drawable.logo_dark
    val BellIcon = R.drawable.bell_icon
    val SearchIcon = R.drawable.search_icon
    val BroadCastIcon = R.drawable.broad_cast_icon
    val OptionIcon = R.drawable.option_icon
    val HistoryIcon = R.drawable.history_icon
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}