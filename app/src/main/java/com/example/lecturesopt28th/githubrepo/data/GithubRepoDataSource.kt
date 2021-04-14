package com.example.lecturesopt28th.githubrepo.data

import com.example.lecturesopt28th.githubrepo.dto.GithubRepoModel
import io.reactivex.Single

interface GithubRepoDataSource {
    fun getGithubRepo(username: String?): Single<GithubRepoModel>
}