package com.example.lecturesopt28th.di

import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSource
import com.example.lecturesopt28th.githubrepo.data.repository.GithubRepoRepository
import com.example.lecturesopt28th.githubrepo.data.repository.GithubRepoRepositoryImpl
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import com.example.lecturesopt28th.home.data.repository.SearchUserRepositoryImpl
import com.example.lecturesopt28th.signup.SignUpDataSource
import com.example.lecturesopt28th.signup.SignUpRepositoryImpl
import com.example.lecturesopt28th.signup.SignUpRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchUserRepository(searchUserDataSource: SearchUserDataSource): SearchUserRepository =
        SearchUserRepositoryImpl(searchUserDataSource)

    @Provides
    @Singleton
    fun provideGithubRepoRepository(githubRepoDataSource: GithubRepoDataSource): GithubRepoRepository =
        GithubRepoRepositoryImpl(githubRepoDataSource)

    @Provides
    @Singleton
    fun provideSignUpRepoRepository(signUpDataSource: SignUpDataSource): SignUpRespository =
        SignUpRepositoryImpl(signUpDataSource)
}