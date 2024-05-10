package com.zaid.basicassignment.feature_home.data.repository

import com.zaid.basicassignment.feature_home.data.HomaApiService
import com.zaid.basicassignment.feature_home.data.resopnse.VideoResponse
import com.zaid.basicassignment.feature_home.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: HomaApiService
) : HomeRepository {
    override suspend fun getData(): Response<List<VideoResponse>> = apiService.getData()
}