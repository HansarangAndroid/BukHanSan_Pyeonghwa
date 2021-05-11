package com.example.lecturesopt28th.login.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.login.LoginRepository
import com.example.lecturesopt28th.login.RequestLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {
    val email = MutableLiveData<String>()
    val password= MutableLiveData<String>()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    //Todo: Input Checker만들어서 Login message 상세하게 처리하기
    @SuppressLint("CheckResult")
    fun login() {
        repository.login(
            RequestLogin(
                email.value,
                password.value
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loginSuccess.value = it.success
            }, {
                _loginSuccess.value = false
            })
    }
}