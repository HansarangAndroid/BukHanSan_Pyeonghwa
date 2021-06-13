package com.example.githubrepo.data.entity

import com.example.core.response.ResponseRepository
import com.example.core.mapper.Mapper

class GithubRepositoryMapper: Mapper<ResponseRepository, GithubRepositoryModel> {
    override fun map(from: ResponseRepository) =
        GithubRepositoryModel(
            from.name,
            from.description,
            from.language.toString(),
            from.html_url
        )
}