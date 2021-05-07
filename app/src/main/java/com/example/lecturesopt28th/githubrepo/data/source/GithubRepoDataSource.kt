package com.example.lecturesopt28th.githubrepo.data.source

import com.example.lecturesopt28th.githubrepo.data.dto.ResponseRepository
import io.reactivex.Single

interface GithubRepoDataSource {
    fun getGithubRepo(username: String?): Single<List<ResponseRepository>>
}