package com.example.lecturesopt28th.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import com.thedeanda.lorem.LoremIpsum
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUserRepository: SearchUserRepository
) : ViewModel() {

    val userId = MutableLiveData<String>()

    private val _userInfo = MutableLiveData<SearchUserModel>()
    val userInfo: LiveData<SearchUserModel>
    get() = _userInfo

    val description = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getUserAccessed() {
        searchUserRepository.getUserInfo(userId.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userInfo.postValue(it)
                description.value = LoremIpsum.getInstance().getWords(1000)
            }, {
                it.printStackTrace()
            })
    }
}