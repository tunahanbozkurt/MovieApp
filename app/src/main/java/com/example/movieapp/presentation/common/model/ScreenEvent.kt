package com.example.movieapp.presentation.common.model

import androidx.annotation.StringRes

sealed class ScreenEvent {
    data class Navigate(val route: String) : ScreenEvent()
    data class ShowToast(@StringRes val msg: Int) : ScreenEvent()
    data class ShowPopup(@StringRes val msg: Int) : ScreenEvent()
}