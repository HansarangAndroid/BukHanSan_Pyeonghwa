package com.example.core.service

import com.example.core.request.RequestLogin
import com.example.core.response.ResponseLogin
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/signin")
    fun login(@Body body: RequestLogin): Single<ResponseLogin>
}