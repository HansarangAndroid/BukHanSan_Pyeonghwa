package com.example.lecturesopt28th.signup.data.source

import com.example.lecturesopt28th.signup.api.SignUpApiService
import com.example.lecturesopt28th.signup.data.dto.RequestSignUp
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    private val signUpApiService: SignUpApiService
){
    fun signUp(requestSignUp: RequestSignUp) = signUpApiService.signup(requestSignUp)
}