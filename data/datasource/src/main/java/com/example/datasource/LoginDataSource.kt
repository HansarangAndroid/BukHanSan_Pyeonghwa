package com.example.datasource

import com.example.data.login.RequestLogin
import com.example.model.login.LoginApiService
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginApiService: LoginApiService
) {
    fun login(requestLogin:RequestLogin) = loginApiService.login(requestLogin)
}