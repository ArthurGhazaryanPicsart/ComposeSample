package com.example.composepicsartsample.ui

sealed class UiState {
    object Loading : UiState()
    class Success(val data : List<Any>) : UiState()
}