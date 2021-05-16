package com.example.lecturesopt28th.home.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.base.BaseViewModel
import com.example.lecturesopt28th.home.data.entity.FollowerModel
import com.example.lecturesopt28th.home.data.entity.UserModel
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
) : BaseViewModel() {

    val userId = MutableLiveData<String?>()

    private val _userModel = MutableLiveData<UiState<UserModel>>()
    val userModel: LiveData<UiState<UserModel>>
        get() = _userModel

    private val _followers = MutableLiveData<UiState<List<FollowerModel>>>()
    val followers: LiveData<UiState<List<FollowerModel>>>
        get() = _followers

    val description = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getUserAccessed() {
        getFollowers()
        _userModel.postValue(UiState.loading(null))
        addDisposable(
            searchUserRepository.getUserInfo(userId.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _userModel.postValue(UiState.success(it))
                    description.value =
                        LoremIpsum.getInstance()
                            .getWords(500)
                }, {
                    _userModel.postValue(UiState.error(null, it.message))
                    it.printStackTrace()
                })
        )
    }

    @SuppressLint("CheckResult")
    fun getFollowers() {
        _followers.postValue(UiState.loading(null))
        addDisposable(
            searchUserRepository.getFollowers(userId.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _followers.postValue(UiState.success(it))
                },{
                    _followers.postValue(UiState.error(null, it.message))
                    it.printStackTrace()
                })
        )
    }
}