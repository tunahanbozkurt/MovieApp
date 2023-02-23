package com.example.movieapp.presentation.auth.reset

sealed class ResetPasswordScreenUIEvent {
    data class EnteredEmail(val email: String) : ResetPasswordScreenUIEvent()
    object Next : ResetPasswordScreenUIEvent()
}
