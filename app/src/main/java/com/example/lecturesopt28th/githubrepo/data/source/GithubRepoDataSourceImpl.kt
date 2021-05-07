package com.example.lecturesopt28th.githubrepo.data.source

import com.example.lecturesopt28th.githubrepo.api.GithubRepoApiService
import com.example.lecturesopt28th.githubrepo.data.dto.ResponseRepository
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoDataSourceImpl @Inject constructor(
    private val githubRepoApiService: GithubRepoApiService
): GithubRepoDataSource {
    override fun getGithubRepo(username: String?): Single<List<ResponseRepository>> {
        return githubRepoApiService.getRepository(username)
    }
}