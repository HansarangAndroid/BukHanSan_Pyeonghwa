package com.example.githubrepo.repository

import com.example.githubrepo.data.entity.GithubRepositoryMapper
import com.example.githubrepo.data.entity.GithubRepositoryModel
import com.example.githubrepo.data.source.GithubRepoDataSource
import io.reactivex.Single
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val dataSource: GithubRepoDataSource,
    private val mapper: GithubRepositoryMapper
): GithubRepoRepository {
    override fun getGithubRepo(username: String?): Single<List<GithubRepositoryModel>> {
        return dataSource.getGithubRepo(username).map { list ->
            list.map { mapper.map(it) } }
    }
}