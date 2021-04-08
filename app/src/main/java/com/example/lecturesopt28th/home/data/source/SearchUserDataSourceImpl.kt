package com.example.lecturesopt28th.home.data.source

import com.example.lecturesopt28th.home.api.SearchUserApiService
import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import io.reactivex.Single
import javax.inject.Inject

class SearchUserDataSourceImpl @Inject constructor(
    private val searchUserApiService: SearchUserApiService
): SearchUserDataSource {
    override fun getUserInfo(userName: String?): Single<SearchUserModel> =
        searchUserApiService.getUserInfo(userName)
}