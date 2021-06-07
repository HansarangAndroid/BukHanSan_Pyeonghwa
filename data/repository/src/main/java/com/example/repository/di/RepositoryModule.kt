package com.example.repository.di

import com.example.datasource.home.SearchUserDataSource
import com.example.datasource.login.LoginDataSource
import com.example.datasource.signup.SignUpDataSource
import com.example.datasource.source.GithubRepoDataSource
import com.example.repository.github.GithubRepoRepository
import com.example.repository.github.GithubRepoRepositoryImpl
import com.example.repository.home.SearchUserRepository
import com.example.repository.home.SearchUserRepositoryImpl
import com.example.repository.login.LoginRepository
import com.example.repository.login.LoginRepositoryImpl
import com.example.repository.signup.SignUpRepository
import com.example.repository.signup.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideSignUpRepository(dataSource: SignUpDataSource): SignUpRepository = SignUpRepositoryImpl(dataSource)

    @Provides
    fun provideLoginRepository(dataSource: LoginDataSource): LoginRepository = LoginRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideSearchUserRepository(searchUserDataSource: SearchUserDataSource): SearchUserRepository =
        SearchUserRepositoryImpl(searchUserDataSource)

    @Provides
    @Singleton
    fun provideGithubRepoRepository(githubRepoDataSource: GithubRepoDataSource): GithubRepoRepository =
        GithubRepoRepositoryImpl(githubRepoDataSource)
}