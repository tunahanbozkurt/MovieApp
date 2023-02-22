package com.example.movieapp.presentation.auth.signup

sealed class SignUpScreenUIEvent {
    data class EnteredName(val name: String) : SignUpScreenUIEvent()
    data class EnteredEmail(val email: String) : SignUpScreenUIEvent()
    data class EnteredPassword(val password: String) : SignUpScreenUIEvent()
    object ShowPassword : SignUpScreenUIEvent()
    object CheckBox : SignUpScreenUIEvent()
    object SignUp : SignUpScreenUIEvent()
}
