package com.example.repository.github

import com.example.datasource.source.GithubRepoDataSource
import com.example.model.github.entity.GithubRepositoryModel
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val dataSource: GithubRepoDataSource
): GithubRepoRepository {
    override fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>> {
        return dataSource.getGithubRepo(username).map { it.map { data ->
            data.toRepository() }
        }
    }
}