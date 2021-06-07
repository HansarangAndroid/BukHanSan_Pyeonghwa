package com.example.repository.home

import com.example.model.home.entity.FollowerModel
import com.example.model.home.entity.UserModel
import io.reactivex.Single

interface SearchUserRepository {
    fun getUserInfo(userName:String?): Single<UserModel>
    fun getFollowers(userName: String?): Single<List<FollowerModel>>
}