package com.example.home.data.mapper

import com.example.core.mapper.Mapper
import com.example.core.response.ResponseUser
import com.example.home.data.entity.UserModel

class UserMapper: Mapper<ResponseUser, UserModel> {
    override fun map(from: ResponseUser): UserModel =
        UserModel(
            from.avatarUrl,
            from.name
        )
}