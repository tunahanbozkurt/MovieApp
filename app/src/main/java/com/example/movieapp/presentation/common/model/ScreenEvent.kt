package com.example.movieapp.presentation.common.model

sealed class ScreenEvent {
    data class Navigate(val route: String) : ScreenEvent()
    data class ShowToast(val msg: String = "") : ScreenEvent()
}