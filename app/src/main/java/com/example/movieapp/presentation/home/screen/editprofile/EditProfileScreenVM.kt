package com.example.movieapp.presentation.home.screen.editprofile

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.presentation.common.model.PasswordFieldState
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenVM @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val currentUser = Firebase.auth.currentUser
    var initDisplayName: String = currentUser?.displayName ?: ""
    var initEmail: String = currentUser?.email ?: ""

    private val _nameFieldState = MutableStateFlow(
        TextFieldState(
            text = initDisplayName
        )
    )
    val nameFieldState = _nameFieldState.asStateFlow()

    private val _emailFieldState = MutableStateFlow(
        TextFieldState(
            text = initEmail
        )
    )
    val emailFieldState = _emailFieldState.asStateFlow()

    private val _passwordFieldState = MutableStateFlow(PasswordFieldState())
    val passwordFieldState = _passwordFieldState.asStateFlow()

    private val _phoneNumber = MutableStateFlow(
        TextFieldState(
            text = currentUser?.phoneNumber ?: ""
        )
    )
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    fun handleUIEvent(event: EditProfileScreenUIEvent) {
        when (event) {
            is EditProfileScreenUIEvent.EnteredEmail -> {
                _emailFieldState.update { it.copy(text = event.email) }
            }
            is EditProfileScreenUIEvent.EnteredName -> {
                _nameFieldState.update { it.copy(text = event.name) }
            }
            is EditProfileScreenUIEvent.EnteredPhoneNumber -> {
                _phoneNumber.update { it.copy(text = event.number) }
            }
            is EditProfileScreenUIEvent.EnteredPassword -> {
                _passwordFieldState.update { it.copy(password = event.password) }
            }
            EditProfileScreenUIEvent.Save -> {
                updateUserInfo(
                    _nameFieldState.value.text,
                    _passwordFieldState.value.password,
                    _emailFieldState.value.text
                )
            }
        }
    }

    private fun updateUserInfo(name: String, password: String, email: String) {
        val nameToUpdate = if (name != initDisplayName) name else null
        val emailToUpdate = if (email != initEmail) email else null
        val update = ((nameToUpdate != null || emailToUpdate != null) && password.isNotEmpty())
        if (update) {

        }

    }
}