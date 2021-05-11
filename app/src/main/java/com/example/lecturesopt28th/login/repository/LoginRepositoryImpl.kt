package com.example.lecturesopt28th.login.repository

import com.example.lecturesopt28th.login.data.source.LoginDataSource
import com.example.lecturesopt28th.login.data.dto.RequestLogin
import com.example.lecturesopt28th.login.data.dto.ResponseLogin
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
): LoginRepository {
    override fun login(requestLogin: RequestLogin): Single<ResponseLogin> =
        dataSource.login(requestLogin)
}