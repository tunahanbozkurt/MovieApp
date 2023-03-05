package com.example.movieapp.presentation.auth.base

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.auth.AuthUseCase
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.navigation.Graph
import com.example.movieapp.util.onSuccess
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationScreenVM @Inject constructor(
    private val authUseCase: AuthUseCase,
    sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _eventChannel = Channel<ScreenEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        with(sharedPreferences.edit()) {
            putBoolean("DONT_SHOW_ONBOARDING", true)
            apply()
        }
    }

    fun signInWithFacebook(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        viewModelScope.launch {
            val response = authUseCase.loginWithCredential(credential)
            if (response.onSuccess()) {
                _eventChannel.send(ScreenEvent.Navigate(Graph.HOME))
            }
        }
    }

    fun signInWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        viewModelScope.launch {
            val response = authUseCase.loginWithCredential(credential)
            if (response.onSuccess()) {
                _eventChannel.send(ScreenEvent.Navigate(Graph.HOME))
            }
        }
    }
}