package com.example.datasource.home

import com.example.model.home.SearchUserApiService
import com.example.model.home.dto.ResponseFollowers
import com.example.model.home.dto.ResponseUser
import io.reactivex.Single
import javax.inject.Inject

class SearchUserDataSourceImpl @Inject constructor(
    private val searchUserApiService: SearchUserApiService
): SearchUserDataSource {
    override fun getUserInfo(userName: String?): Single<ResponseUser> =
        searchUserApiService.getUserInfo(userName)

    override suspend fun getFollowers(userName: String?): List<ResponseFollowers> =
        searchUserApiService.getFollowers(userName)

//    override fun getFollowers(userName: String?): Single<List<ResponseFollowers>> =
//        searchUserApiService.getFollowers(userName)
}