package com.example.lecturesopt28th.home.data.repository

import com.example.lecturesopt28th.home.data.entity.FollowerModel
import com.example.lecturesopt28th.home.data.entity.UserModel
import io.reactivex.Single

interface SearchUserRepository {
    fun getUserInfo(userName:String?): Single<UserModel>
    fun getFollowers(userName: String?): Single<List<FollowerModel>>
}