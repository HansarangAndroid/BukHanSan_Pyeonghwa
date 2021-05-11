package com.example.lecturesopt28th.signup.repository

import com.example.lecturesopt28th.signup.data.dto.RequestSignUp
import com.example.lecturesopt28th.signup.data.dto.ResponseSingUp
import io.reactivex.Single

interface SignUpRespository {
    fun signUp(requestSignUp: RequestSignUp): Single<ResponseSingUp>
}