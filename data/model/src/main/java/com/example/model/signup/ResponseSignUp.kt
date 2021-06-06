package com.example.model.signup

data class ResponseSignUp(
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val nickname: String
    )
}