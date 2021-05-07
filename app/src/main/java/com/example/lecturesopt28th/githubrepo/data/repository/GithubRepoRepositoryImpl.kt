package com.example.lecturesopt28th.githubrepo.data.repository

import com.example.lecturesopt28th.githubrepo.data.entity.GithubRepositoryModel
import com.example.lecturesopt28th.githubrepo.data.source.GithubRepoDataSource
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val dataSource: GithubRepoDataSource
): GithubRepoRepository {
    override fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>> {
        return dataSource.getGithubRepo(username).map { it.map { data ->
            data.toRepository() } }
    }
}