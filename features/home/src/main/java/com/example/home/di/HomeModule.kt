package com.example.home.di

import com.example.core.service.GithubApiService
import com.example.home.repository.SearchUserRepository
import com.example.home.repository.SearchUserRepositoryImpl
import com.example.home.data.source.SearchUserDataSource
import com.example.home.data.source.SearchUserDataSourceImpl
import com.example.home.data.mapper.FollowerMapper
import com.example.home.data.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    @Singleton
    fun provideFollowerMapper(): FollowerMapper = FollowerMapper()

    @Provides
    @Singleton
    fun provideSearchUserDataSource(githubApiService: GithubApiService): SearchUserDataSource = SearchUserDataSourceImpl(githubApiService)

    @Provides
    @Singleton
    fun provideSearchUserRepository(
        searchUserDataSource: SearchUserDataSource,
        userMapper: UserMapper,
        followerMapper: FollowerMapper
    ): SearchUserRepository =
        SearchUserRepositoryImpl(searchUserDataSource, userMapper, followerMapper)
}