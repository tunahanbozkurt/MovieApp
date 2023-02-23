package com.example.movieapp.domain.usecase.auth

data class AuthUseCase(
    val createUser: CreateUser,
    val loginUser: LoginUser,
    val resetPassword: ResetPassword,
    val loginWithCredential: LoginWithCredential
)
