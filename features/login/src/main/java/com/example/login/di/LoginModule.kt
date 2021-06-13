package com.example.login.di

import com.example.core.service.LoginApiService
import com.example.login.controller.LoginController
import com.example.login.controller.LoginControllerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    fun provideLoginController(loginApiService: LoginApiService): LoginController = LoginControllerImpl(loginApiService)

}