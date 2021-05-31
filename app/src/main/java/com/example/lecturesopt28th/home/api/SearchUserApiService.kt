package com.example.lecturesopt28th.home.api

import com.example.lecturesopt28th.home.data.dto.ResponseFollowers
import com.example.lecturesopt28th.home.data.dto.ResponseUser
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchUserApiService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String?): Single<ResponseUser>

    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String?): Response<List<ResponseFollowers>>

//    @GET("/users/{username}/followers")
//    fun getFollowers(@Path("username") username: String?): Single<List<ResponseFollowers>>
}