package com.example.lecturesopt28th.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LogInViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val password= MutableLiveData<String>()
}