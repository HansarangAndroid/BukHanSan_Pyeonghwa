package com.example.model.github.entity

data class GithubRepositoryModel (
    val repositoryName : String?,
    val description: String?,
    var language: String?,
    val url: String?
)