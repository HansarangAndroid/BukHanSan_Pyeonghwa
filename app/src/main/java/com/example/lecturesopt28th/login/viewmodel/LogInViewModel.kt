package com.example.lecturesopt28th.login.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.login.RequestLogin
import com.example.lecturesopt28th.base.BaseViewModel
import com.example.lecturesopt28th.login.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: LoginRepository
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
            repository.login(
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