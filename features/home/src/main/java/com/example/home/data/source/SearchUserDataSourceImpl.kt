package com.example.home.data.source

import com.example.core.response.ResponseFollowers
import com.example.core.response.ResponseUser
import com.example.core.service.GithubApiService
import com.example.home.data.source.SearchUserDataSource
import io.reactivex.Single
import javax.inject.Inject

class SearchUserDataSourceImpl @Inject constructor(
    private val searchUserApiService: GithubApiService
): SearchUserDataSource {
    override fun getUserInfo(userName: String?): Single<ResponseUser> =
        searchUserApiService.getUserInfo(userName)

    override fun getFollowers(userName: String?): Single<List<ResponseFollowers>> =
        searchUserApiService.getFollowers(userName)
}