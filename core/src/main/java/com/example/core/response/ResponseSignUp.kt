package com.example.core.response

data class ResponseSignUp(
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val nickname: String
    )
}