package com.example.lecturesopt28th.signup

import io.reactivex.Single

interface SignUpRespository {
    fun signUp(requestSignUp: RequestSignUp): Single<ResponseSingUp>
}