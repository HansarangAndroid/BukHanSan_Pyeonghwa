package com.example.lecturesopt28th.githubrepo.data

import com.example.lecturesopt28th.githubrepo.dto.GithubRepoModel
import com.example.lecturesopt28th.githubrepo.data.GithubRepoDataSource
import com.example.lecturesopt28th.githubrepo.data.GithubRepoRepository
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val dataSource: GithubRepoDataSource
): GithubRepoRepository {
    override fun getGithubRepo(username: String?): Single<GithubRepoModel> {
        return dataSource.getGithubRepo(username)
    }
}