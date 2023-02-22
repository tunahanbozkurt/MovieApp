package com.example.movieapp.data

import com.example.movieapp.R
import com.example.movieapp.domain.AuthenticationRepository
import com.example.movieapp.util.TaskResult
import com.google.firebase.auth.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): TaskResult {
        return withContext(ioDispatcher) {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                return@withContext TaskResult.Success

            } catch (e: FirebaseAuthWeakPasswordException) {
                return@withContext TaskResult.Error(R.string.weak_password)

            } catch (e: FirebaseAuthInvalidCredentialsException) {
                return@withContext TaskResult.Error(R.string.incorrect_email_form)

            } catch (e: FirebaseAuthUserCollisionException) {
                return@withContext TaskResult.Error(R.string.user_already_exists)
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): TaskResult {
        return withContext(ioDispatcher) {
            try {
                auth.signInWithEmailAndPassword(email, password)
                return@withContext TaskResult.Success

            } catch (e: FirebaseAuthInvalidUserException) {
                return@withContext TaskResult.Error(R.string.invalid_user)

            } catch (e: FirebaseAuthInvalidCredentialsException) {
                return@withContext TaskResult.Error(R.string.wrong_password)
            }
        }
    }
}