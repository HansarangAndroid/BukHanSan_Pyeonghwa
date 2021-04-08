package com.example.lecturesopt28th.home.data.repository

import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import io.reactivex.Single

interface SearchUserRepository {
    fun getUserInfo(userName:String?): Single<SearchUserModel>
}