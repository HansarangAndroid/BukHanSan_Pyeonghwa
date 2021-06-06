package com.example.repository.github

import com.example.model.github.entity.GithubRepositoryModel
import io.reactivex.Single

interface GithubRepoRepository {
    fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>>
}