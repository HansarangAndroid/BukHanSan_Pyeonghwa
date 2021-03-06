package com.example.model.home

import com.example.model.home.dto.ResponseFollowers
import com.example.model.home.dto.ResponseUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchUserApiService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String?): Single<ResponseUser>

    @GET("/users/{username}/followers")
    fun getFollowers(@Path("username") username: String?): Single<List<ResponseFollowers>>
}