package com.example.movieapp.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.AuthenticationRepository
import com.example.movieapp.presentation.common.model.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenVM @Inject constructor(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    private val _nameFieldState = MutableStateFlow(TextFieldState())
    val nameFieldState = _nameFieldState.asStateFlow()

    private val _emailFieldState = MutableStateFlow(TextFieldState())
    val emailFieldState = _emailFieldState.asStateFlow()

    private val _passwordFieldState = MutableStateFlow(PasswordFieldState())
    val passwordFieldState = _passwordFieldState.asStateFlow()

    private val _checkBoxState = MutableStateFlow(false)
    val checkBoxState = _checkBoxState.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun handleUIEvent(event: SignUpScreenUIEvent) {
        when (event) {
            is SignUpScreenUIEvent.EnteredEmail -> {
                _emailFieldState.update { it.copy(text = event.email) }
            }
            is SignUpScreenUIEvent.EnteredName -> {
                _nameFieldState.update { it.copy(text = event.name) }
            }
            is SignUpScreenUIEvent.EnteredPassword -> {
                _passwordFieldState.update { it.copy(password = event.password) }
            }
            SignUpScreenUIEvent.ShowPassword -> {
                _passwordFieldState.update { it.copy(isVisible = !it.isVisible) }
            }
            SignUpScreenUIEvent.SignUp -> {
                createUser(_emailFieldState.value.text, _passwordFieldState.value.password)
            }
            SignUpScreenUIEvent.CheckBox -> {
                _checkBoxState.update { !it }
            }
        }
    }

    private fun createUser(email: String, password: String) {
        val hasError = checkFields(
            name = _nameFieldState.value.text,
            email = _emailFieldState.value.text,
            password = _passwordFieldState.value.password,
            checkBox = _checkBoxState.value
        )
        if (!hasError) {
            viewModelScope.launch {
                authRepository.createUserWithEmailAndPassword(email, password)
            }
        }
    }

    private fun checkFields(
        name: String,
        email: String,
        password: String,
        checkBox: Boolean
    ): Boolean {
        val nameCheck = name.length < 3
        val passwordCheck = password.length < 6
        val emailCheck = email.isEmpty()

        if (nameCheck) {
            _nameFieldState.update { it.copy(hasError = true) }
        }
        if (passwordCheck) {
            _passwordFieldState.update { it.copy(hasError = true) }
        }
        if (emailCheck) {
            _emailFieldState.update { it.copy(hasError = true) }
        }

        if (!checkBox) {
            _eventChannel.trySend(ScreenEvent.ShowToast)
        }

        val hasError = arrayListOf(nameCheck, passwordCheck, emailCheck, !checkBox).any {
            it
        }

        return hasError
    }
}