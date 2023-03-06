package com.example.movieapp.presentation.home.screen.editprofile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.repository.AuthenticationRepository
import com.example.movieapp.presentation.common.model.PasswordFieldState
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.model.TextFieldState
import com.example.movieapp.util.constants.SharedPref
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenVM @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val currentUser = Firebase.auth.currentUser
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

    private val _profileImageUri = MutableStateFlow<String?>(null)
    val profileImageUri = _profileImageUri.asStateFlow()

    private val _eventChannel = Channel<ScreenEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    init {
        getProfileImageUri()
    }

    fun handleUIEvent(event: EditProfileScreenUIEvent) {
        when (event) {
            is EditProfileScreenUIEvent.EnteredEmail -> {
                _emailFieldState.update { it.copy(text = event.email) }
            }
            is EditProfileScreenUIEvent.EnteredName -> {
                _nameFieldState.update { it.copy(text = event.name) }
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

    fun getProfileImageUri() {
        val imagePath = sharedPreferences.getString(SharedPref.PROFILE_IMAGE_BASE64, null)
        imagePath?.let {
            _profileImageUri.update { imagePath }
        }
    }

    fun setProfileImageBase64(img: String?) {
        with(sharedPreferences.edit()) {
            putString(SharedPref.PROFILE_IMAGE_BASE64, img)
            apply()
        }
    }

    private fun updateUserInfo(name: String, password: String, email: String) {
        val updateName = name != initDisplayName
        val updateEmail = email != initEmail

        if (password.isNotEmpty()) {
            viewModelScope.launch {
                if (updateName && updateEmail) {
                    authenticationRepository.updateDisplayName(name, password)
                    authenticationRepository.updateEmail(initEmail, email, password)
                    with(sharedPreferences.edit()) {
                        putString(SharedPref.USER_NAME, name)
                        apply()
                    }
                    _eventChannel.send(ScreenEvent.Navigate(""))
                    _eventChannel.send(ScreenEvent.ShowToast(R.string.email_and_name_updated))
                    return@launch
                }
                if (updateName) {
                    authenticationRepository.updateDisplayName(name, password)
                    with(sharedPreferences.edit()) {
                        putString(SharedPref.USER_NAME, name)
                        apply()
                    }
                    _eventChannel.send(ScreenEvent.Navigate(""))
                    _eventChannel.send(ScreenEvent.ShowToast(R.string.name_updated))
                    return@launch
                }
                if (updateEmail) {
                    authenticationRepository.updateEmail(initEmail, email, password)
                    _eventChannel.send(ScreenEvent.Navigate(""))
                    _eventChannel.send(ScreenEvent.ShowToast(R.string.email_updated))
                    return@launch
                }
            }
        } else {
            _eventChannel.trySend(ScreenEvent.ShowToast(R.string.enter_password))
        }
    }
}