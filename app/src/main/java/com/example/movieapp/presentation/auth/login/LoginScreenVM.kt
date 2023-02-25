package com.example.movieapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.auth.AuthUseCase
import com.example.movieapp.domain.usecase.field.CheckFieldUseCase
import com.example.movieapp.presentation.common.model.PasswordFieldState
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
import com.example.movieapp.presentation.navigation.Graph
import com.example.movieapp.util.hasError
import com.example.movieapp.util.onError
import com.example.movieapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenVM @Inject constructor(
    private val checkFieldUseCase: CheckFieldUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _emailFieldState = MutableStateFlow(TextFieldState())
    val emailFieldState = _emailFieldState.asStateFlow()

    private val _passwordFieldState = MutableStateFlow(PasswordFieldState())
    val passwordFieldState = _passwordFieldState.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun handleUIEvent(event: LoginScreenUIEvent) {
        when (event) {
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
                val hasError = checkFields(
                    email = _emailFieldState.value.text,
                    password = _passwordFieldState.value.password
                )
                if (!hasError) {
                    viewModelScope.launch {
                        val response = authUseCase.loginUser(
                            _emailFieldState.value.text,
                            _passwordFieldState.value.password
                        )
                        if (response.onSuccess()) {
                            _eventChannel.trySend(ScreenEvent.Navigate(Graph.HOME))
                        } else {
                            /*TODO*/
                        }
                    }
                }
            }
        }
    }

    private fun checkFields(email: String, password: String): Boolean {
        val emailValidation = checkFieldUseCase.checkEmailField(email)
        val passwordValidation = checkFieldUseCase.checkPasswordField(password)

        _emailFieldState.update { it.copy(hasError = emailValidation.onError()) }
        _passwordFieldState.update { it.copy(hasError = passwordValidation.onError()) }

        return listOf(emailValidation, passwordValidation).hasError()
    }
}