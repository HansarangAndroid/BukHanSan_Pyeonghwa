package com.example.lecturesopt28th.githubrepo.data

import com.example.lecturesopt28th.githubrepo.api.GithubRepoApiService
import com.example.lecturesopt28th.githubrepo.data.GithubRepoDataSource
import com.example.lecturesopt28th.githubrepo.dto.GithubRepoModel
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoDataSourceImpl @Inject constructor(
    private val githubRepoApiService: GithubRepoApiService
): GithubRepoDataSource {
    override fun getGithubRepo(username: String?): Single<GithubRepoModel> {
        return githubRepoApiService.getRepository(username)
    }
}