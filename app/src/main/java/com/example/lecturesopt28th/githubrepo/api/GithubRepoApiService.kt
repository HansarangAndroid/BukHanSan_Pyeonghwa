package com.example.lecturesopt28th.githubrepo.api

import com.example.lecturesopt28th.githubrepo.dto.GithubRepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepoApiService {
    @GET("/users/{username}/repos")
    fun getRepository(@Path("username") username: String?): Single<GithubRepoModel>
}