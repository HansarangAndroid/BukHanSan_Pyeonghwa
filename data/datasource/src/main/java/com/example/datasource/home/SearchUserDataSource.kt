package com.example.datasource.home

import com.example.model.home.dto.ResponseFollowers
import com.example.model.home.dto.ResponseUser
import io.reactivex.Single

interface SearchUserDataSource {
    fun getUserInfo(userName: String?): Single<ResponseUser>
    fun getFollowers(userName: String?) : Single<List<ResponseFollowers>>
}