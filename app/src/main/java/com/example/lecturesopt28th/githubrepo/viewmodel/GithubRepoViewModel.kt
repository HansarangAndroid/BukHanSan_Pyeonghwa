package com.example.lecturesopt28th.githubrepo.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.githubrepo.data.GithubRepoRepository
import com.example.lecturesopt28th.githubrepo.dto.GithubRepoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val repository: GithubRepoRepository
) : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _repositories = MutableLiveData<GithubRepoModel>()
    val repositories: LiveData<GithubRepoModel>
        get() = _repositories

    fun changeUserName(name: String){
        _userName.value = name
    }

    @SuppressLint("CheckResult")
    fun getGithubRepo() {
        repository.getGithubRepo(userName.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       _repositories.postValue(it)
            }, {
                it.printStackTrace()
                Log.e("failed get repository ","fucking error")
            })
    }
}