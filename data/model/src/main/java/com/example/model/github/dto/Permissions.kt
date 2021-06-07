package com.example.model.github.dto

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)