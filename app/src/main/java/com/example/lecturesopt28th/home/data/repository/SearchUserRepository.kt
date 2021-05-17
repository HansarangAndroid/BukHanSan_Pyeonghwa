package com.example.lecturesopt28th.home.data.repository

import com.example.lecturesopt28th.home.data.entity.FollowerModel
import com.example.lecturesopt28th.home.data.entity.UserModel
import io.reactivex.Single
import retrofit2.Response

interface SearchUserRepository {
    fun getUserInfo(userName:String?): Single<UserModel>
    suspend fun getFollowers(userName: String?): List<FollowerModel>
//    fun getFollowers(userName: String?): Single<List<FollowerModel>>
}