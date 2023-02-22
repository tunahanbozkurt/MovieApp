package com.example.movieapp.presentation.auth.signup

data class PasswordFieldState(
    val password: String = "",
    val isVisible: Boolean = false,
    val hasError: Boolean = false
)
