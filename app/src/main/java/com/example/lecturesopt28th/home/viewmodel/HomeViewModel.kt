package com.example.lecturesopt28th.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import com.example.lecturesopt28th.utils.UiState
import com.thedeanda.lorem.LoremIpsum
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUserRepository: SearchUserRepository
) : ViewModel() {

    val userId = MutableLiveData<String?>()

    private val _userModel = MutableLiveData<UiState<SearchUserModel>>()
    val userModel: LiveData<UiState<SearchUserModel>>
        get() = _userModel

    val description = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getUserAccessed() {
        _userModel.postValue(UiState.loading(null))
        searchUserRepository.getUserInfo(userId.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userModel.postValue(UiState.success(it))
                description.value = LoremIpsum.getInstance().getWords(1000)
            }, {
                _userModel.postValue(UiState.error(null, it.message))
                it.printStackTrace()
            })
    }
}