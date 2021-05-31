package com.example.lecturesopt28th.signup.data.dto

data class RequestSignUp(
    val email: String?,
    val password: String?,
    val sex: String?,
    val nickname: String?,
    val phone: String?,
    val birth: String?
)