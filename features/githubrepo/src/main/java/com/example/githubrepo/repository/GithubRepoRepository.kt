package com.example.githubrepo.repository

import com.example.githubrepo.data.entity.GithubRepositoryModel
import io.reactivex.Single

interface GithubRepoRepository {
    fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>>
}