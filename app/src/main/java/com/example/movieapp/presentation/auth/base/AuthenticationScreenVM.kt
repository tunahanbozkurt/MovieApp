package com.example.movieapp.presentation.auth.base

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.auth.AuthUseCase
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationScreenVM @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    init {
        with(sharedPreferences.edit()) {
            putBoolean("DONT_SHOW_ONBOARDING", true)
            apply()
        }
    }

    fun signInWithFacebook(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        println(credential)
        viewModelScope.launch {
            authUseCase.loginWithCredential(credential)
        }
    }

    fun signInWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        viewModelScope.launch {
            authUseCase.loginWithCredential(credential)
        }
    }
}