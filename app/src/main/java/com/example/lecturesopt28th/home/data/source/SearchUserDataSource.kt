package com.example.lecturesopt28th.home.data.source

import com.example.lecturesopt28th.home.data.dto.ResponseFollowers
import com.example.lecturesopt28th.home.data.dto.ResponseUser
import io.reactivex.Single
import retrofit2.Response

interface SearchUserDataSource {
    fun getUserInfo(userName: String?): Single<ResponseUser>
    suspend fun getFollowers(userName: String?) : List<ResponseFollowers>
//    fun getFollowers(userName: String?) : Single<List<ResponseFollowers>>
}