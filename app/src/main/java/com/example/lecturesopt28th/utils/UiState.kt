package com.example.lecturesopt28th.utils

import com.example.lecturesopt28th.home.data.dto.SearchUserModel

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: SearchUserModel): UiState()
    data class Error(val message: String): UiState()
}