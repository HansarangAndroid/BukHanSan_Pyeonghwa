package com.example.datasource.di

import com.example.datasource.LoginDataSource
import com.example.datasource.SignUpDataSource
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
}