package com.example.datasource.signup

import com.example.model.signup.RequestSignUp
import com.example.model.signup.SignUpApiService
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    private val signUpApiService: SignUpApiService
){
    fun signUp(requestSignUp: RequestSignUp) = signUpApiService.signup(requestSignUp)
}