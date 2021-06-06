package com.example.datasource.source

import com.example.model.github.GithubApiService
import com.example.model.github.dto.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoDataSourceImpl @Inject constructor(
    private val githubRepoApiService: GithubApiService
): GithubRepoDataSource {
    override fun getGithubRepo(username: String?): Single<List<ResponseRepository>> {
        return githubRepoApiService.getRepository(username)
    }
}