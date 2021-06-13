package com.example.core.service

import com.example.core.request.RequestSignUp
import com.example.core.response.ResponseSignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiService {
    @POST("login/signup")
    fun signup(@Body body: RequestSignUp): Single<ResponseSignUp>
}