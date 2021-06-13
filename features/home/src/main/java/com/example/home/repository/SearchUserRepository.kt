package com.example.home.repository

import com.example.home.data.entity.FollowerModel
import com.example.home.data.entity.UserModel
import io.reactivex.Single

interface SearchUserRepository {
    fun getUserInfo(userName:String?): Single<UserModel>
    fun getFollowers(userName: String?): Single<List<FollowerModel>>
}