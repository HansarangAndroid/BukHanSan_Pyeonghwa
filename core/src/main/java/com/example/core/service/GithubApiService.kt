package com.example.core.service

import com.example.core.response.ResponseRepository
import com.example.core.response.ResponseFollowers
import com.example.core.response.ResponseUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String?): Single<ResponseUser>

    @GET("/users/{username}/followers")
    fun getFollowers(@Path("username") username: String?): Single<List<ResponseFollowers>>

    @GET("/users/{username}/repos")
    fun getRepository(@Path("username") username: String?): Single<List<ResponseRepository>>
}