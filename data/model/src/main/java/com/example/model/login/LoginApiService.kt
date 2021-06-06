package com.example.model.login

import com.example.data.login.RequestLogin
import com.example.data.login.ResponseLogin
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/signin")
    fun login(@Body body: RequestLogin): Single<ResponseLogin>
}