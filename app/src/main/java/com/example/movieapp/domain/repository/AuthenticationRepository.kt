package com.example.movieapp.domain.repository

import com.example.movieapp.util.TaskResult
import com.google.firebase.auth.AuthCredential

interface AuthenticationRepository {

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String
    ): TaskResult

    suspend fun signInWithEmailAndPassword(email: String, password: String): TaskResult
    suspend fun sendPasswordResetEmail(email: String): TaskResult
    suspend fun signInWithCredential(credential: AuthCredential): TaskResult
    suspend fun updateDisplayName(name: String, password: String): TaskResult
    suspend fun updateEmail(originalEmail: String, email: String, password: String): TaskResult
}