package com.example.lecturesopt28th.login.repository

import com.example.data.login.RequestLogin
import com.example.data.login.ResponseLogin
import io.reactivex.Single

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}