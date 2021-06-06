package com.example.repository.signup

import com.example.datasource.signup.SignUpDataSource
import com.example.model.signup.RequestSignUp
import com.example.model.signup.ResponseSignUp
import io.reactivex.Single
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataSource: SignUpDataSource
): SignUpRepository {
    override fun signUp(requestSignUp: RequestSignUp): Single<ResponseSignUp> =
        dataSource.signUp(requestSignUp)
}