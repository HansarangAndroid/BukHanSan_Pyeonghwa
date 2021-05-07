package com.example.lecturesopt28th.githubrepo.data.repository

import com.example.lecturesopt28th.githubrepo.data.entity.GithubRepositoryModel
import io.reactivex.Single

interface GithubRepoRepository {
    fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>>
}