package com.example.githubrepo.data.source


import com.example.core.response.ResponseRepository
import com.example.githubrepo.service.GithubApiService
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoDataSourceImpl @Inject constructor(
    private val githubRepoApiService: GithubApiService
): GithubRepoDataSource {
    override fun getGithubRepo(username: String?): Single<List<ResponseRepository>> {
        return githubRepoApiService.getRepository(username)
    }
}