package com.example.lecturesopt28th.login

import io.reactivex.Single

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>
}