package com.example.model.signup

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiService {
    @POST("login/signup")
    fun signup(@Body body: RequestSignUp): Single<ResponseSignUp>
}