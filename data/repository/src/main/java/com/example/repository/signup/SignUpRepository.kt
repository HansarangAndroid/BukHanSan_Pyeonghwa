package com.example.repository.signup

import com.example.model.signup.RequestSignUp
import com.example.model.signup.ResponseSignUp
import io.reactivex.Single

interface SignUpRepository {
    fun signUp(requestSignUp: RequestSignUp): Single<ResponseSignUp>
}