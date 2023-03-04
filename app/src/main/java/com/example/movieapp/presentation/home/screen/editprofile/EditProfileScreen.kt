package com.example.movieapp.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.presentation.home.elements.EditProfileInfoSection
import com.example.movieapp.presentation.home.screen.editprofile.EditProfileScreenUIEvent
import com.example.movieapp.presentation.home.screen.editprofile.EditProfileScreenVM
import com.example.movieapp.util.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditProfileScreen(
    viewModel: EditProfileScreenVM = hiltViewModel()
) {
    val context = LocalContext.current
    val nameState = viewModel.nameFieldState.collectAsState().value
    val passwordState = viewModel.passwordFieldState.collectAsState().value
    val emailState = viewModel.emailFieldState.collectAsState().value
    val phoneNumberState = viewModel.phoneNumber.collectAsState().value
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ScreenEvent.Navigate -> {

                }
                is ScreenEvent.ShowToast -> {
                    context.showToast(event.msg)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
    ) {
        EditProfileInfoSection()
        VerticalSpacer(heightDp = 40)
        CommonTextField(
            text = nameState.text,
            labelText = "Full Name",
            hasError = false,
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredName(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = emailState.text,
            labelText = "Email",
            hasError = false,
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredEmail(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = passwordState.password,
            labelText = "Password",
            hasError = passwordState.hasError,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredPassword(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = phoneNumberState.text,
            labelText = "Phone Number",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            hasError = false,
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredPhoneNumber(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 40)
        BlueButton(
            buttonText = "Save Changes",
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.handleUIEvent(EditProfileScreenUIEvent.Save)
        }
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}