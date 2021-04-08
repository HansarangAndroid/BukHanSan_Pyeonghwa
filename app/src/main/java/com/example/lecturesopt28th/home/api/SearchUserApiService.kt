package com.example.lecturesopt28th.home.api

import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchUserApiService {
    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String?): Single<SearchUserModel>
}