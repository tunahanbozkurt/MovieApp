package com.example.movieapp.domain.usecase.auth

import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.util.TaskResult
import com.google.firebase.auth.AuthCredential

class LoginWithCredential(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(credential: AuthCredential): TaskResult {
        return authenticationRepository.signInWithCredential(credential)
    }
}