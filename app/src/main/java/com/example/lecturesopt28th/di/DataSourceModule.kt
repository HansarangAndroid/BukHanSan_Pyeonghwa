package com.example.lecturesopt28th.di

import com.example.lecturesopt28th.githubrepo.api.GithubRepoApiService
import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSource
import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSourceImpl
import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import com.example.lecturesopt28th.home.data.source.SearchUserDataSourceImpl
import com.example.lecturesopt28th.signup.SignUpApiService
import com.example.lecturesopt28th.signup.SignUpDataSource
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

    @Provides
    @Singleton
    fun provideGithubRepoDataSource(githubRepoApiService: GithubRepoApiService): GithubRepoDataSource = GithubRepoDataSourceImpl(githubRepoApiService)

    @Provides
    @Singleton
    fun provideSoptDataSource(signUpApiService: SignUpApiService) = SignUpDataSource(signUpApiService)
}