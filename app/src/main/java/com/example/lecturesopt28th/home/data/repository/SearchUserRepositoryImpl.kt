package com.example.lecturesopt28th.home.data.repository

import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import io.reactivex.Single
import javax.inject.Inject

class SearchUserRepositoryImpl @Inject constructor(
    private val searchUserDataSource: SearchUserDataSource
): SearchUserRepository {
    override fun getUserInfo(userName: String?): Single<SearchUserModel> =
        searchUserDataSource.getUserInfo(userName)
}