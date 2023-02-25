package com.example.movieapp.presentation.auth.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.auth.AuthUseCase
import com.example.movieapp.domain.usecase.field.CheckFieldUseCase
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
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
class ResetPasswordScreenVM @Inject constructor(
    private val checkFieldUseCase: CheckFieldUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _emailFieldState = MutableStateFlow(TextFieldState())
    val emailFieldState = _emailFieldState.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun handleUIEvent(event: ResetPasswordScreenUIEvent) {
        when (event) {
            is ResetPasswordScreenUIEvent.EnteredEmail -> {
                _emailFieldState.update { it.copy(text = event.email) }
            }
            ResetPasswordScreenUIEvent.Next -> {
                val emailValidation = checkFieldUseCase.checkEmailField(_emailFieldState.value.text)
                _emailFieldState.update { it.copy(hasError = emailValidation.onError()) }
                if (emailValidation.onSuccess()) {
                    viewModelScope.launch {
                        authUseCase.resetPassword(_emailFieldState.value.text)
                    }
                }
            }
        }
    }
}
