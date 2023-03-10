package com.example.movieapp.data.repository

import com.example.movieapp.R
import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.util.TaskResult
import com.example.movieapp.util.extensions.safeFirebaseRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String
    ): TaskResult {
        return safeFirebaseRequest(
            ioDispatcher,
            execute = { auth.createUserWithEmailAndPassword(email, password) },
            then = {
                it.user?.updateProfile(userProfileChangeRequest { displayName = name })
            }
        )
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

    override suspend fun updateDisplayName(name: String, password: String): TaskResult {
        return safeFirebaseRequest(ioDispatcher) {
            auth.currentUser?.updateProfile(
                userProfileChangeRequest { displayName = name }
            )
        }
    }

    override suspend fun updateEmail(
        originalEmail: String,
        email: String,
        password: String
    ): TaskResult {
        return try {
            auth.currentUser?.reauthenticate(
                EmailAuthProvider.getCredential(originalEmail, password)
            )?.await()

            auth.currentUser?.updateEmail(email)?.await()
            TaskResult.Success

        } catch (e: Exception) {
            e.printStackTrace()
            TaskResult.Error(R.string.invalid_user)
        }
    }
}