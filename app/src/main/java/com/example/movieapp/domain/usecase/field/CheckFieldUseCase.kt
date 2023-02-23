package com.example.movieapp.domain.usecase.field

data class CheckFieldUseCase(
    val checkEmailField: CheckEmailField,
    val checkNameField: CheckNameField,
    val checkPasswordField: CheckPasswordField
)
