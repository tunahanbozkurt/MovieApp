package com.example.movieapp.presentation.home.screen.editprofile

sealed class EditProfileScreenUIEvent {
    data class EnteredName(val name: String) : EditProfileScreenUIEvent()
    data class EnteredPassword(val password: String) : EditProfileScreenUIEvent()
    data class EnteredEmail(val email: String) : EditProfileScreenUIEvent()
    data class EnteredPhoneNumber(val number: String) : EditProfileScreenUIEvent()
    object Save : EditProfileScreenUIEvent()
}
