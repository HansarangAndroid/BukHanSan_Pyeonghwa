package com.example.lecturesopt28th.login.api

import com.example.lecturesopt28th.login.data.dto.RequestLogin
import com.example.lecturesopt28th.login.data.dto.ResponseLogin
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/signin")
    fun login(@Body body: RequestLogin): Single<ResponseLogin>
}