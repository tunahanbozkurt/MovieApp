package com.example.movieapp.domain

import com.example.movieapp.util.TaskResult

interface AuthenticationRepository {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): TaskResult
    suspend fun signInWithEmailAndPassword(email: String, password: String): TaskResult
}