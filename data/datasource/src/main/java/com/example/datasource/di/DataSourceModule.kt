package com.example.datasource.di

import com.example.datasource.home.SearchUserDataSource
import com.example.datasource.home.SearchUserDataSourceImpl
import com.example.datasource.login.LoginDataSource
import com.example.datasource.signup.SignUpDataSource
import com.example.datasource.source.GithubRepoDataSource
import com.example.datasource.source.GithubRepoDataSourceImpl
import com.example.model.github.GithubApiService
import com.example.model.home.SearchUserApiService
import com.example.model.login.LoginApiService
import com.example.model.signup.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideSignUpDataSource(signUpApiService: SignUpApiService): SignUpDataSource = SignUpDataSource(signUpApiService)

    @Provides
    @Singleton
    fun provideLoginDataSource(loginApiService: LoginApiService) = LoginDataSource(loginApiService)

    @Provides
    @Singleton
    fun provideDataSource(searchUserApiService: SearchUserApiService): SearchUserDataSource = SearchUserDataSourceImpl(searchUserApiService)

    @Provides
    @Singleton
    fun provideGithubRepoDataSource(githubRepoApiService: GithubApiService): GithubRepoDataSource = GithubRepoDataSourceImpl(githubRepoApiService)
}