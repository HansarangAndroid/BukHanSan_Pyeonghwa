package com.example.login.controller

import com.example.core.request.RequestLogin
import com.example.core.response.ResponseLogin
import com.example.core.service.LoginApiService
import com.example.login.controller.LoginController
import io.reactivex.Single
import javax.inject.Inject

class LoginControllerImpl @Inject constructor(
    private val loginApiService: LoginApiService
): LoginController {
    override fun login(requestLogin: RequestLogin): Single<ResponseLogin> =
        loginApiService.login(requestLogin)
}