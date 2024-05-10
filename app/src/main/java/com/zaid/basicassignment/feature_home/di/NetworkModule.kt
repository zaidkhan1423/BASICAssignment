package com.zaid.basicassignment.feature_home.di

import com.zaid.basicassignment.feature_home.data.HomaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMainApiService(retrofit: Retrofit): HomaApiService =
        retrofit.create(HomaApiService::class.java)

}