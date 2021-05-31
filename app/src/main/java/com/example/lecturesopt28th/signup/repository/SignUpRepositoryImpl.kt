package com.example.lecturesopt28th.signup.repository

import com.example.lecturesopt28th.signup.data.dto.RequestSignUp
import com.example.lecturesopt28th.signup.data.dto.ResponseSingUp
import com.example.lecturesopt28th.signup.data.source.SignUpDataSource
import io.reactivex.Single
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
): SignUpRespository {
    override fun signUp(requestSignUp: RequestSignUp): Single<ResponseSingUp> =
        dataSource.signUp(requestSignUp)
}