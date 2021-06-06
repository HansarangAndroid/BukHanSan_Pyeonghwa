package com.example.lecturesopt28th.signup.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lecturesopt28th.base.BaseViewModel
import com.example.lecturesopt28th.utils.InputChecker.checkBlank
import com.example.lecturesopt28th.utils.InputChecker.checkEmailPattern
import com.example.model.signup.RequestSignUp
import com.example.repository.signup.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val _sex = MutableLiveData<String>()
    private val sex: LiveData<String>
        get() = _sex

    private val _isSuccessed = MutableLiveData<Boolean>()
    val isSuccessed: LiveData<Boolean>
        get() = _isSuccessed

    private val _isValueEmpty = MutableLiveData<Boolean>()
    val isValueEmpty: LiveData<Boolean>
        get() = _isValueEmpty

    fun changeSex(sex: Int) {
        _sex.value = sex.toString()
    }

    fun checkEmailValidation(): Boolean {
        return checkEmailPattern(email.value ?: "")
    }

    fun getBlankValue() {
        val list = listOf(email, password, nickname, birthday, sex)
        _isValueEmpty.value = checkBlank(list) <= 0
    }

    @SuppressLint("CheckResult")
    fun getSignUpResult() {
        addDisposable(
            signUpRepository.signUp(
                RequestSignUp(
                    email = email.value,
                    password = password.value,
                    sex = sex.value,
                    nickname = nickname.value,
                    phone = phoneNumber.value,
                    birth = birthday.value
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isSuccessed.postValue(it.success)
                }, {
                    _isSuccessed.postValue(false)
                })
        )
    }
}