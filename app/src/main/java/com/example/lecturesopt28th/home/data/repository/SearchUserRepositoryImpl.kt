package com.example.lecturesopt28th.home.data.repository

import com.example.lecturesopt28th.home.data.entity.FollowerModel
import com.example.lecturesopt28th.home.data.entity.UserModel
import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import io.reactivex.Single
import javax.inject.Inject

class SearchUserRepositoryImpl @Inject constructor(
    private val searchUserDataSource: SearchUserDataSource
) : SearchUserRepository {
    override fun getUserInfo(userName: String?): Single<UserModel> =
        searchUserDataSource.getUserInfo(userName).map { it.toUserModel() }

    override fun getFollowers(userName: String?): Single<List<FollowerModel>> =
        searchUserDataSource.getFollowers(userName).map {
            it.map { data ->
                data.toFollowerModel()
            }
        }
}