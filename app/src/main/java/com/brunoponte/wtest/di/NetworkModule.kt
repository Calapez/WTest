package com.brunoponte.wget.di

import com.brunoponte.wtest.network.IRequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRequestService() : IRequestService {
        return Retrofit.Builder()
            .baseUrl("https://5bb1cd166418d70014071c8e.mockapi.io/mobile/1-1/articles/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IRequestService::class.java)
    }

}