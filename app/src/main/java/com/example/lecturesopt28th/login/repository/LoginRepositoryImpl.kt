package com.example.lecturesopt28th.login.repository

import com.example.data.login.RequestLogin
import com.example.data.login.ResponseLogin
import com.example.datasource.LoginDataSource
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
): LoginRepository {
    override fun login(requestLogin: RequestLogin): Single<ResponseLogin> =
        dataSource.login(requestLogin)
}