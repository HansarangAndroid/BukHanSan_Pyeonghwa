package com.example.lecturesopt28th.githubrepo.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lecturesopt28th.base.BaseViewModel
import com.example.lecturesopt28th.githubrepo.data.entity.GithubRepositoryModel
import com.example.lecturesopt28th.githubrepo.data.repository.GithubRepoRepository
import com.example.lecturesopt28th.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GithubRepoViewModel @Inject constructor(
    private val repository: GithubRepoRepository
) : BaseViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _repositories = MutableLiveData<UiState<MutableList<GithubRepositoryModel>>>()
    val repositories: LiveData<UiState<MutableList<GithubRepositoryModel>>>
        get() = _repositories

    private val _backUpRepo = MutableLiveData<GithubRepositoryModel>()
    val backUpRepo: LiveData<GithubRepositoryModel>
        get() = _backUpRepo

    val switchStatus = MutableLiveData<Boolean>()

    fun changeUserName(name: String) {
        _userName.value = name
    }

    fun changeBackUpRepo(repo: GithubRepositoryModel) {
        _backUpRepo.value = repo
    }

    fun removeRepository(position: Int) {
        _repositories.value?.data?.removeAt(position)
    }

    fun insertRepository(position: Int, repo: GithubRepositoryModel?) {
        if (repo != null) {
            _repositories.value?.data?.add(position, repo)
        }
    }

    fun remapRepositories(currentPosition: Int, targetPosition: Int) {
        val datas = _repositories.value?.data
        if (currentPosition < targetPosition) {
            for (i in currentPosition until targetPosition) {
                datas?.set(i, datas.set(i + 1, datas[i]))
            }
        } else {
            for (i in currentPosition..targetPosition + 1) {
                datas?.set(i, datas.set(i - 1, datas[i]))
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getGithubRepo() {
        _repositories.postValue(UiState.loading(null))
        addDisposable(
            repository.getGithubRepo(userName.value)
                .map { it.toMutableList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _repositories.postValue(UiState.success(it))
                }, {
                    it.printStackTrace()
                    Log.e("why fucking error ", it.message.toString())
                    _repositories.postValue(UiState.error(null, it.message))
                })
        )
    }
}