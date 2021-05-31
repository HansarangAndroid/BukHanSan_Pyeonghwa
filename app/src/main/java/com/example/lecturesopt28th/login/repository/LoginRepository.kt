package com.example.lecturesopt28th.login.repository

import com.example.lecturesopt28th.login.data.dto.RequestLogin
import com.example.lecturesopt28th.login.data.dto.ResponseLogin
import io.reactivex.Single

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}