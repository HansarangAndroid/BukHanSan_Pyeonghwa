package com.example.lecturesopt28th.login.data.source

import com.example.lecturesopt28th.login.api.LoginApiService
import com.example.lecturesopt28th.login.data.dto.RequestLogin
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
){
    fun login(requestLogin: RequestLogin) = loginApiService.login(requestLogin)
}