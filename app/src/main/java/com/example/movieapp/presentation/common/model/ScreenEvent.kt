package com.example.movieapp.presentation.common.model

sealed class ScreenEvent {
    object Navigate : ScreenEvent()
    object ShowToast : ScreenEvent()
}