package com.example.home.data.source

import com.example.core.response.ResponseFollowers
import com.example.core.response.ResponseUser
import io.reactivex.Single

interface SearchUserDataSource {
    fun getUserInfo(userName: String?): Single<ResponseUser>
    fun getFollowers(userName: String?) : Single<List<ResponseFollowers>>
}