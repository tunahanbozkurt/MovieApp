package com.example.movieapp.domain.usecase.auth

import com.example.movieapp.domain.AuthenticationRepository
import com.example.movieapp.util.TaskResult

class LoginUser(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String): TaskResult {
        return authenticationRepository.signInWithEmailAndPassword(email, password)
    }
}