package com.example.githubrepo.service

import com.example.githubrepo.data.dto.ResponseRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("/users/{username}/repos")
    fun getRepository(@Path("username") username: String?): Single<List<ResponseRepository>>
}