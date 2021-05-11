package com.example.lecturesopt28th.di

import com.example.lecturesopt28th.githubrepo.api.GithubRepoApiService
import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSource
import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSourceImpl
import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import com.example.lecturesopt28th.home.data.source.SearchUserDataSourceImpl
import com.example.lecturesopt28th.login.api.LoginApiService
import com.example.lecturesopt28th.login.data.source.LoginDataSource
import com.example.lecturesopt28th.signup.api.SignUpApiService
import com.example.lecturesopt28th.signup.data.source.SignUpDataSource
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
    fun provideSignUpDataSource(signUpApiService: SignUpApiService) = SignUpDataSource(signUpApiService)

    @Provides
    @Singleton
    fun provideLoginDataSource(loginApiService: LoginApiService) = LoginDataSource(loginApiService)
}