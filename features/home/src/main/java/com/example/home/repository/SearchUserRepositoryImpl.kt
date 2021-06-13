package com.example.home.repository

import com.example.home.data.source.SearchUserDataSource
import com.example.home.data.entity.FollowerModel
import com.example.home.data.entity.UserModel
import com.example.home.data.mapper.FollowerMapper
import com.example.home.data.mapper.UserMapper
import io.reactivex.Single
import javax.inject.Inject

class SearchUserRepositoryImpl @Inject constructor(
    private val searchUserDataSource: SearchUserDataSource,
    private val userMapper: UserMapper,
    private val followerMapper: FollowerMapper
) : SearchUserRepository {
    override fun getUserInfo(userName: String?): Single<UserModel> =
        searchUserDataSource.getUserInfo(userName).map { userMapper.map(it) }

    override fun getFollowers(userName: String?): Single<List<FollowerModel>> =
        searchUserDataSource.getFollowers(userName).map { followers ->
            followers.map { followerMapper.map(it)   }
        }
}