package com.example.core.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}