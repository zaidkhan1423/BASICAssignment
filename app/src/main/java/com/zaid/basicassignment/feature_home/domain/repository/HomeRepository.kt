package com.zaid.basicassignment.feature_home.domain.repository

import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import retrofit2.Response

interface HomeRepository {

    suspend fun getData(): Response<List<VideoResponse>>

}