package com.example.movieapp.presentation.common.model

data class PasswordFieldState(
    val password: String = "",
    val isVisible: Boolean = false,
    val hasError: Boolean = false
)
