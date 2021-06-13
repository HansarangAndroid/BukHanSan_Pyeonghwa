package com.example.home.data.mapper

import com.example.core.mapper.Mapper
import com.example.core.response.ResponseFollowers
import com.example.home.data.entity.FollowerModel

class FollowerMapper: Mapper<ResponseFollowers, FollowerModel> {
    override fun map(from: ResponseFollowers): FollowerModel =
        FollowerModel(
            from.avatar_url,
            from.html_url
        )
}