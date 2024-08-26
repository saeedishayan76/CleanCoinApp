package com.shayan.data.di

import com.shayan.data.dto.ApiService
import com.shayan.data.repository.CoinRepoImpl
import com.shayan.data.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCoinRepository(apiService: ApiService): CoinRepository {
        return CoinRepoImpl(apiService)
    }


}