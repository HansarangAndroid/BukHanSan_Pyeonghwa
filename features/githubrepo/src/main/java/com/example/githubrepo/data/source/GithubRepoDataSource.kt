package com.example.githubrepo.data.source

import com.example.core.response.ResponseRepository
import io.reactivex.Single

interface GithubRepoDataSource {
    fun getGithubRepo(username: String?): Single<List<ResponseRepository>>
}