package com.example.movieapp.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.PasswordField
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.BlueText
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.presentation.navigation.AuthenticationScreen
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.uppercaseFirst
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {

    val context = LocalContext.current
    val name = viewModel.name
    val passwordState = viewModel.passwordFieldState.collectAsState().value
    val emailState = viewModel.emailFieldState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ScreenEvent.Navigate -> {
                    navigate.invoke(event.route)
                }
                else -> return@collectLatest
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {

        VerticalSpacer(heightDp = 40)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = if (name.isNotEmpty()) "Hi, ${name.uppercaseFirst()}" else "Hi",
                style = MaterialTheme.localFont.semiBoldH2,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )

            VerticalSpacer(heightDp = 8)

            Text(
                text = "Welcome back! Please enter your details",
                style = MaterialTheme.localFont.mediumH6,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }

        VerticalSpacer(heightDp = 64)

        CommonTextField(
            text = emailState.text,
            labelText = "Email",
            hasError = emailState.hasError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.handleUIEvent(LoginScreenUIEvent.EnteredEmail(it)) }
        )

        VerticalSpacer(heightDp = 24)

        PasswordField(
            password = passwordState.password,
            isVisible = passwordState.isVisible,
            hasError = passwordState.hasError,
            modifier = Modifier.fillMaxWidth(),
            iconClick = { viewModel.handleUIEvent(LoginScreenUIEvent.ShowPassword) },
            onValueChange = { viewModel.handleUIEvent(LoginScreenUIEvent.EnteredPassword(it)) }
        )

        VerticalSpacer(heightDp = 8)

        BlueText(
            text = "Forgot Password?",
            style = MaterialTheme.localFont.mediumH6,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    navigate(AuthenticationScreen.ResetPassword.route)
                }
        )

        VerticalSpacer(heightDp = 40)
        BlueButton(
            buttonText = context.getString(R.string.login),
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.handleUIEvent(LoginScreenUIEvent.Login)
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen {}
}