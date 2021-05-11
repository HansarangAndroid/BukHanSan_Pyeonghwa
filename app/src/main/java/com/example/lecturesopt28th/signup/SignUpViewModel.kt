package com.example.lecturesopt28th.signup

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRespository
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()

    val _sex = MutableLiveData<Int>()
    val sex: LiveData<Int>
        get() = _sex

    private val _isSuccessed = MutableLiveData<Boolean>()
    val isSuccessed: LiveData<Boolean>
        get() = _isSuccessed

    fun changeSex(sex: Int) {
        _sex.value = sex
    }

    fun checkInputEverything(): Boolean {
        return !(email.value.isNullOrEmpty()
                && password.value.isNullOrEmpty()
                && nickname.value.isNullOrEmpty()
                && phoneNumber.value.isNullOrEmpty()
                && birthday.value.isNullOrEmpty()
                && sex.value != null)
    }

    @SuppressLint("CheckResult")
    fun signUp() {
        if (checkInputEverything()) {
            signUpRepository.signUp(
                RequestSignUp(
                    email = email.value,
                    password = password.value,
                    sex = sex.value.toString(),
                    nickname = nickname.value,
                    phone = phoneNumber.value,
                    birth = birthday.value
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           _isSuccessed.value = true
                }, {
                    _isSuccessed.value = false
                })
        }
    }

}