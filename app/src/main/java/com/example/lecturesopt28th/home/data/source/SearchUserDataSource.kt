package com.example.lecturesopt28th.home.data.source

import com.example.lecturesopt28th.home.data.dto.ResponseFollowers
import com.example.lecturesopt28th.home.data.dto.ResponseUser
import io.reactivex.Single

interface SearchUserDataSource {
    fun getUserInfo(userName: String?): Single<ResponseUser>
    fun getFollowers(userName: String?) : Single<List<ResponseFollowers>>
}