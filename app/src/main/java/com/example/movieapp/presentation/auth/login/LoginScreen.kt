package com.example.movieapp.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.*
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun LoginScreen(
    viewModel: LoginScreenVM = hiltViewModel()
) {

    val context = LocalContext.current
    val passwordState = viewModel.passwordFieldState.collectAsState().value
    val emailState = viewModel.emailFieldState.collectAsState().value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.localColor.primaryDark)
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TitleCouple(bigTitle = "Hi, Tiffany", infoText = "Welcome back! Please enter your details")

        VerticalSpacer(height = 64)
        
        CommonTextField(
            text = emailState.text,
            labelText = "Email",
            hasError = emailState.hasError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {}
        )

        VerticalSpacer(height = 24)

        PasswordField(
            password = "password",
            isVisible = false,
            hasError = false,
            modifier = Modifier.fillMaxWidth(),
            iconClick = { viewModel.handleUIEvent(LoginScreenUIEvent.ShowPassword) },
            onValueChange = { viewModel.handleUIEvent(LoginScreenUIEvent.EnteredPassword(it)) }
        )

        VerticalSpacer(height = 8)

        Text(
            text = "Forgot Password?",
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.primaryBlueAccent,
            modifier = Modifier.align(Alignment.End)
        )

        VerticalSpacer(height = 40)
        BlueButton(buttonText = context.getString(R.string.login), modifier = Modifier.fillMaxWidth()) {
            
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}