package com.example.movieapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.example.movieapp.presentation.common.model.PasswordFieldState
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenVM @Inject constructor(): ViewModel() {

    private val _emailFieldState = MutableStateFlow(TextFieldState())
    val emailFieldState = _emailFieldState.asStateFlow()

    private val _passwordFieldState = MutableStateFlow(PasswordFieldState())
    val passwordFieldState = _passwordFieldState.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun handleUIEvent(event: LoginScreenUIEvent) {
        when(event) {
            is LoginScreenUIEvent.EnteredEmail -> {
                _emailFieldState.update { it.copy(event.email) }
            }
            is LoginScreenUIEvent.EnteredPassword -> {
                _passwordFieldState.update { it.copy(event.password) }
            }
            LoginScreenUIEvent.ShowPassword -> {
                _passwordFieldState.update { it.copy(isVisible = !it.isVisible) }
            }
            LoginScreenUIEvent.ForgotPassword -> {

            }
            LoginScreenUIEvent.Login -> {

            }
        }
    }
}