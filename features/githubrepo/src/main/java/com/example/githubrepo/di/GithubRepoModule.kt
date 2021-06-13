package com.example.githubrepo.di

import com.example.githubrepo.data.entity.GithubRepositoryMapper
import com.example.githubrepo.repository.GithubRepoRepository
import com.example.githubrepo.repository.GithubRepoRepositoryImpl
import com.example.githubrepo.service.GithubApiService
import com.example.githubrepo.data.source.GithubRepoDataSource
import com.example.githubrepo.data.source.GithubRepoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubRepoModule {

    @Provides
    @Singleton
    fun provideGithubRepoDataSource(githubRepoApiService: GithubApiService): GithubRepoDataSource = GithubRepoDataSourceImpl(githubRepoApiService)

    @Provides
    @Singleton
    fun provideGithubRepositoryMapper():GithubRepositoryMapper = GithubRepositoryMapper()

    @Provides
    @Singleton
    fun provideGithubRepoRepository(
        githubRepoDataSource: GithubRepoDataSource,
        repositoryMapper: GithubRepositoryMapper
    ): GithubRepoRepository =
        GithubRepoRepositoryImpl(githubRepoDataSource,repositoryMapper)
}