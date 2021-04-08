package com.example.lecturesopt28th.di

import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import com.example.lecturesopt28th.home.data.source.SearchUserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideDataSource(searchUserApiService: SearchUserApiService): SearchUserDataSource = SearchUserDataSourceImpl(searchUserApiService)
}