package com.example.datasource.source

import com.example.model.github.dto.ResponseRepository
import io.reactivex.Single

interface GithubRepoDataSource {
    fun getGithubRepo(username: String?): Single<List<ResponseRepository>>
}