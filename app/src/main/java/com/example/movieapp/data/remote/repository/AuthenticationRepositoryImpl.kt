package com.example.movieapp.data.remote.repository

import com.example.movieapp.domain.AuthenticationRepository
import com.example.movieapp.util.TaskResult
import com.example.movieapp.util.safeFirebaseRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): TaskResult {
        return safeFirebaseRequest(ioDispatcher) {
            auth.createUserWithEmailAndPassword(email, password)
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): TaskResult {
        return safeFirebaseRequest(ioDispatcher) {
            auth.signInWithEmailAndPassword(email, password)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): TaskResult {
       return safeFirebaseRequest(ioDispatcher) {
           auth.sendPasswordResetEmail(email)
       }
    }

    override suspend fun signInWithCredential(credential: AuthCredential): TaskResult {
        return safeFirebaseRequest(ioDispatcher) {
            auth.signInWithCredential(credential)
        }
    }
}