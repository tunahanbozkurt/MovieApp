package com.example.movieapp.domain.repository

import com.example.movieapp.util.TaskResult
import com.google.firebase.auth.AuthCredential

interface AuthenticationRepository {

    /**
     * Sign in the user with email and password by using firebase authentication.
     * Returns the result of request.
     */
    suspend fun signInWithEmailAndPassword(email: String, password: String): TaskResult

    /**
     * Sends password reset email to the user by using firebase authentication.
     * Returns the result of request.
     */
    suspend fun sendPasswordResetEmail(email: String): TaskResult

    /**
     * Sign in the user with credential by using firebase authentication.
     * Returns the result of request.
     */
    suspend fun signInWithCredential(credential: AuthCredential): TaskResult

    /**
     * Updates the user's display name by using firebase authentication.
     * Returns the result of request.
     */
    suspend fun updateDisplayName(name: String, password: String): TaskResult

    /**
     * Updates the user's email by using firebase authentication.
     * Returns the result of request.
     */
    suspend fun updateEmail(originalEmail: String, email: String, password: String): TaskResult

    /**
     * Creates a new user with email and password by using firebase authentication
     * Returns the result of request.
     */
    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String
    ): TaskResult
}