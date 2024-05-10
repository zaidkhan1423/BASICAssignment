package com.zaid.basicassignment.feature_home.data

import com.zaid.basicassignment.core.utils.Endpoints.API_KEY
import com.zaid.basicassignment.core.utils.Endpoints.VIDEOS
import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomaApiService {

    @Headers("Authorization: $API_KEY", "Apikey: $API_KEY")
    @GET(VIDEOS)
    suspend fun getData(@Query("select") select: String = "*"): Response<List<VideoResponse>>

}