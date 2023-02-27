package com.example.movieapp.domain.usecase.auth

import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.util.TaskResult

class ResetPassword(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String): TaskResult {
        return authenticationRepository.sendPasswordResetEmail(email)
    }
}