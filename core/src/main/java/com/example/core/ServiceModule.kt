package com.example.core

import com.example.core.service.GithubApiService
import com.example.core.service.LoginApiService
import com.example.core.service.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService = retrofit.create(GithubApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService = retrofit.create(LoginApiService::class.java)

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService = retrofit.create(SignUpApiService::class.java)

}