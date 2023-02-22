package com.example.movieapp.presentation.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.CommonTextField
import com.example.movieapp.presentation.common.PasswordField
import com.example.movieapp.presentation.common.TitleCouple
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.onboarding.common.BlueButton
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    viewModel: SignUpScreenVM = hiltViewModel()
) {

    val context = LocalContext.current
    val nameField = viewModel.nameFieldState.collectAsState().value
    val emailField = viewModel.emailFieldState.collectAsState().value
    val passwordField = viewModel.passwordFieldState.collectAsState().value
    val checkBox = viewModel.checkBoxState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                ScreenEvent.Navigate -> TODO()
                ScreenEvent.ShowToast -> {
                    context.showToast("You need to accept Terms and Services and Privacy Policy")
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.localColor.primaryDark)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {


        TitleCouple(
            bigTitle = "Let's get started",
            infoText = "The latest movies and series are here"
        )

        Spacer(modifier = Modifier.height(64.dp))

        CommonTextField(
            text = nameField.text,
            labelText = "Full Name",
            modifier = Modifier.fillMaxWidth(),
            hasError = nameField.hasError,
            onValueChange = { viewModel.handleUIEvent(SignUpScreenUIEvent.EnteredName(it)) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CommonTextField(
            text = emailField.text,
            labelText = "Email Address",
            modifier = Modifier.fillMaxWidth(),
            hasError = emailField.hasError,
            onValueChange = { viewModel.handleUIEvent(SignUpScreenUIEvent.EnteredEmail(it)) }
        )
        Spacer(modifier = Modifier.height(24.dp))

        PasswordField(
            password = passwordField.password,
            isVisible = passwordField.isVisible,
            hasError = passwordField.hasError,
            iconClick = { viewModel.handleUIEvent(SignUpScreenUIEvent.ShowPassword) },
            onValueChange = {
                viewModel.handleUIEvent(SignUpScreenUIEvent.EnteredPassword(it))
            })

        val annotatedString = buildAnnotatedString {
            append("By joining, you agree to the ")

            pushStringAnnotation(tag = "terms and services", annotation = "terms")
            withStyle(style = SpanStyle(color = MaterialTheme.localColor.primaryBlueAccent)) {
                append("Terms and Services")
            }
            pop()

            append(" and ")

            pushStringAnnotation(tag = "privacy policy", annotation = "privacy")
            withStyle(style = SpanStyle(color = MaterialTheme.localColor.primaryBlueAccent)) {
                append(" Privacy Policy")
            }

            pop()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = checkBox,
                onCheckedChange = { viewModel.handleUIEvent(SignUpScreenUIEvent.CheckBox) })
            Text(
                text = annotatedString,
                style = MaterialTheme.localFont.mediumBody,
                color = MaterialTheme.localColor.textGrey
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        BlueButton(
            buttonText = context.getString(R.string.sign_up),
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.handleUIEvent(SignUpScreenUIEvent.SignUp)
        }

    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}