package com.example.repository.login

import com.example.data.login.RequestLogin
import com.example.data.login.ResponseLogin
import io.reactivex.Single

interface LoginRepository {
    fun login(requestLogin: RequestLogin): Single<ResponseLogin>

}