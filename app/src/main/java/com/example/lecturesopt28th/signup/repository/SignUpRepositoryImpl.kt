package com.example.lecturesopt28th.signup.repository

import com.example.datasource.SignUpDataSource
import com.example.model.signup.RequestSignUp
import com.example.model.signup.ResponseSignUp
import io.reactivex.Single
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
): SignUpRespository {
    override fun signUp(requestSignUp: RequestSignUp): Single<ResponseSignUp> =
        dataSource.signUp(requestSignUp)
}