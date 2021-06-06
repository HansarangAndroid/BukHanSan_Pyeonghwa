package com.example.lecturesopt28th.home.data.source

import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.dto.ResponseFollowers
import com.example.lecturesopt28th.home.data.dto.ResponseUser
import io.reactivex.Single
import retrofit2.Response
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