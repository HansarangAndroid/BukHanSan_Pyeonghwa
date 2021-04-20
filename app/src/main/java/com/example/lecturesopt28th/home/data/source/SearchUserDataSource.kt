package com.example.lecturesopt28th.home.data.source

import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import io.reactivex.Single

interface SearchUserDataSource {
    fun getUserInfo(userName: String?): Single<SearchUserModel>
}