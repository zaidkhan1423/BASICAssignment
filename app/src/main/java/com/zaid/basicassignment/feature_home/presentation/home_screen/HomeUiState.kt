package com.zaid.basicassignment.feature_home.presentation.home_screen

import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import com.zaid.basicassignment.feature_home.presentation.data.VideoResponseLocal

data class HomeUiState(
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val videoResponse: List<VideoResponseLocal> = emptyList()
)
