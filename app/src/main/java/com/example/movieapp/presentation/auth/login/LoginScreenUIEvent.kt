package com.example.movieapp.presentation.auth.login

sealed class LoginScreenUIEvent {
    data class EnteredEmail(val email: String) : LoginScreenUIEvent()
    data class EnteredPassword(val password: String) : LoginScreenUIEvent()
    object ShowPassword : LoginScreenUIEvent()
    object ForgotPassword : LoginScreenUIEvent()
    object Login : LoginScreenUIEvent()
}
