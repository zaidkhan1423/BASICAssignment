package com.zaid.basicassignment.feature_home.presentation.data

import android.os.Parcelable
import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoResponseLocal(
    val channelName: String,
    val createAt: String,
    val description: String,
    val id: Int,
    val likes: String,
    val thumbnailUrl: String,
    val title: String,
    val userPicUrl: String
) : Parcelable

fun VideoResponse.toLocal() = VideoResponseLocal(
    channelName = channel_name,
    createAt = createAt,
    description = description,
    id = id,
    likes = likes,
    thumbnailUrl = thumbnail_url,
    title = title,
    userPicUrl = user_pic_url
)

fun List<VideoResponse>.toLocal() = map(VideoResponse::toLocal)