package com.example.movieapp.presentation.auth.login

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.PasswordField
import com.example.movieapp.presentation.common.icon.RectangularIcon
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.BlueText
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.presentation.navigation.AuthenticationScreen
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.uppercaseFirst
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenVM = hiltViewModel(),
    showPopup: (Int) -> Unit,
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
                is ScreenEvent.ShowPopup -> {
                    showPopup.invoke(event.msg)
                }
                is ScreenEvent.ShowToast -> {

                }
            }
        }
    }


    Box {
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
                    text = if (name.isNotEmpty()) "${stringResource(id = R.string.hi_comma)} ${name.uppercaseFirst()}" else stringResource(
                        id = R.string.hi
                    ),
                    style = MaterialTheme.localFont.semiBoldH2,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                )

                VerticalSpacer(heightDp = 8)

                Text(
                    text = stringResource(id = R.string.welcome_back),
                    style = MaterialTheme.localFont.mediumH6,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                )
            }

            VerticalSpacer(heightDp = 64)

            CommonTextField(
                text = emailState.text,
                labelText = stringResource(id = R.string.email),
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
                text = stringResource(id = R.string.forgot_password),
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
}

@Composable
fun MessagePopup(
    @StringRes strRes: Int,
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.localColor.primarySoft,
    shape: Shape = RoundedCornerShape(16.dp),
    onCancel: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .background(backGroundColor)

    ) {

        RectangularIcon(
            iconResId = R.drawable.ic_close,
            backGroundColor = MaterialTheme.localColor.primaryDark,
            iconTint = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
                .clickable { onCancel.invoke() }
                .align(Alignment.TopEnd)
                .padding(top = 13.dp, end = 19.dp)
        )

        Text(
            text = stringResource(id = strRes),
            textAlign = TextAlign.Center,
            style = MaterialTheme.localFont.mediumH6,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(showPopup = {}, navigate = {})
}