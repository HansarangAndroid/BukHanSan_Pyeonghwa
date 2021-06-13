package com.example.login.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.commons.BaseViewModel
import com.example.core.request.RequestLogin
import com.example.login.controller.LoginController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginController: LoginController
) : BaseViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    private val _getEmptyCount = MutableLiveData<Boolean>()
    val getEmptyCount: LiveData<Boolean>
        get() = _getEmptyCount

    @SuppressLint("CheckResult")
    fun getLoginResult() {
        addDisposable(
            loginController.login(
                RequestLogin(
                    email.value,
                    password.value
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _loginSuccess.postValue(it.success)
                }, {
                    _loginSuccess.postValue(false)
                })
        )
    }
}