package com.example.core.request

data class RequestSignUp(
    val email: String?,
    val password: String?,
    val sex: String?,
    val nickname: String?,
    val phone: String?,
    val birth: String?
)