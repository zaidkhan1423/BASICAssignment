package com.zaid.basicassignment.feature_home.data.resopnse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoResponse(
    val channel_name: String,
    val createAt: String,
    val description: String,
    val id: Int,
    val likes: String,
    val thumbnail_url: String,
    val title: String,
    val user_pic_url: String
): Parcelable