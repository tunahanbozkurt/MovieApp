package com.example.movieapp.presentation.auth.signup

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.domain.usecase.field.CheckFieldUseCase
import com.example.movieapp.presentation.common.model.PasswordFieldState
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
import com.example.movieapp.presentation.navigation.Graph
import com.example.movieapp.util.constants.SharedPref
import com.example.movieapp.util.extensions.hasError
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
class SignUpScreenVM @Inject constructor(
    private val authRepository: AuthenticationRepository,
    private val checkFieldUseCase: CheckFieldUseCase,
    private val sharedPreferences: SharedPreferences
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
                createUser(
                    _emailFieldState.value.text,
                    _passwordFieldState.value.password,
                    _nameFieldState.value.text
                )
            }
            SignUpScreenUIEvent.CheckBox -> {
                _checkBoxState.update { !it }
            }
        }
    }

    private fun createUser(email: String, password: String, name: String) {
        val hasError = checkFields(
            name = _nameFieldState.value.text,
            email = _emailFieldState.value.text,
            password = _passwordFieldState.value.password,
            checkBox = _checkBoxState.value
        )
        if (!hasError) {
            viewModelScope.launch {
                val response = authRepository.createUserWithEmailAndPassword(email, password, name)
                if (response.onSuccess()) {
                    with(sharedPreferences.edit()) {
                        putString(SharedPref.USER_NAME, _nameFieldState.value.text)
                        apply()
                    }
                    _eventChannel.send(ScreenEvent.Navigate(Graph.HOME))
                } else {
                    /*TOOD*/

                }
            }
        }
    }

    private fun checkFields(
        name: String,
        email: String,
        password: String,
        checkBox: Boolean
    ): Boolean {
        val nameValidation = checkFieldUseCase.checkNameField(name)
        val passwordValidation = checkFieldUseCase.checkPasswordField(password)
        val emailValidation = checkFieldUseCase.checkEmailField(email)

        _nameFieldState.update { it.copy(hasError = nameValidation.onError()) }
        _passwordFieldState.update { it.copy(hasError = passwordValidation.onError()) }
        _emailFieldState.update { it.copy(hasError = emailValidation.onError()) }

        if (!checkBox) {
            _eventChannel.trySend(ScreenEvent.ShowToast(R.string.need_to_accept_privacy))
        }

        return listOf(nameValidation, passwordValidation, emailValidation, !checkBox).hasError()
    }
}