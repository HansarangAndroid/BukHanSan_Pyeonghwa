package com.example.repository.home

import com.example.datasource.home.SearchUserDataSource
import com.example.model.home.entity.FollowerModel
import com.example.model.home.entity.UserModel
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