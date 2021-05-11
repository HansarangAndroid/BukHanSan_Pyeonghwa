package com.example.lecturesopt28th.signup.api

import com.example.lecturesopt28th.signup.data.dto.RequestSignUp
import com.example.lecturesopt28th.signup.data.dto.ResponseSingUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiService {
    @POST("login/signup")
    fun signup(@Body body: RequestSignUp): Single<ResponseSingUp>
}