package com.example.lecturesopt28th.signup

import io.reactivex.Single
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
): SignUpRespository {
    override fun signUp(requestSignUp: RequestSignUp): Single<ResponseSingUp> =
        dataSource.signUp(requestSignUp)
}