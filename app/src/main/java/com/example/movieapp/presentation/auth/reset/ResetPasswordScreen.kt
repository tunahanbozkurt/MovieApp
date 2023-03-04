package com.example.movieapp.presentation.auth.reset

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.TitleCouple
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.ui.theme.localFont
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ResetPasswordScreenVM = hiltViewModel(),
    navigate: () -> Unit
) {

    val nameField = viewModel.emailFieldState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest {
            navigate.invoke()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {

        TitleCouple(
            bigTitle = "Reset Password",
            infoText = "Recover your account password",
            titleStyle = MaterialTheme.localFont.mediumH1,
        )

        VerticalSpacer(heightDp = 48)

        CommonTextField(
            text = nameField.text,
            labelText = "Email Address",
            hasError = nameField.hasError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.handleUIEvent(ResetPasswordScreenUIEvent.EnteredEmail(it)) }
        )

        VerticalSpacer(heightDp = 40)

        BlueButton(
            buttonText = "Next",
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.handleUIEvent(ResetPasswordScreenUIEvent.Next)
        }
    }
}

@Preview
@Composable
fun PreviewResetPasswordScreen() {
    ResetPasswordScreen {}
}