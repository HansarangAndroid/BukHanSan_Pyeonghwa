package com.example.lecturesopt28th.login

import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
){
    fun login(requestLogin: RequestLogin) = loginApiService.login(requestLogin)
}