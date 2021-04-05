package com.example.lecturesopt28th.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()


}