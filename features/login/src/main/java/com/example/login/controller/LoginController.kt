package com.example.login.controller

import com.example.core.request.RequestLogin
import com.example.core.response.ResponseLogin
import io.reactivex.Single

interface LoginController {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}