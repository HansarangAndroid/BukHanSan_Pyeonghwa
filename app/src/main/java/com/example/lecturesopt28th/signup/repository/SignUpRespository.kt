package com.example.lecturesopt28th.signup.repository

import com.example.model.signup.RequestSignUp
import com.example.model.signup.ResponseSignUp
import io.reactivex.Single

interface SignUpRespository {
    fun signUp(requestSignUp: RequestSignUp): Single<ResponseSignUp>
}