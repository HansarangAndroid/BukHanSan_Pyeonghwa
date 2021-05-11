package com.example.lecturesopt28th.signup

import io.reactivex.Single
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    private val signUpApiService: SignUpApiService
){
    fun signUp(requestSignUp: RequestSignUp) = signUpApiService.signup(requestSignUp)
}