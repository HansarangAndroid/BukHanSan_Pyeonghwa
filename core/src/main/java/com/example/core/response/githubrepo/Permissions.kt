package com.example.core.response.githubrepo

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)