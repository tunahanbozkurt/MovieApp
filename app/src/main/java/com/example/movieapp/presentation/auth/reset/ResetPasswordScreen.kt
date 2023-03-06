package com.example.movieapp.presentation.auth.reset

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.TitleCouple
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.showToast
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ResetPasswordScreenVM = hiltViewModel(),
    navigate: () -> Unit
) {

    val nameField = viewModel.emailFieldState.collectAsState().value
    val onBackDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ScreenEvent.Navigate -> {
                    if (event.route.isEmpty()) {
                        onBackDispatcher?.onBackPressed()
                    }
                }

                is ScreenEvent.ShowToast -> {
                    context.showToast(event.msg)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {

        TitleCouple(
            bigTitle = stringResource(id = R.string.reset_password),
            infoText = stringResource(id = R.string.recover_password),
            titleStyle = MaterialTheme.localFont.mediumH1,
        )

        VerticalSpacer(heightDp = 48)

        CommonTextField(
            text = nameField.text,
            labelText = stringResource(id = R.string.email_address),
            hasError = nameField.hasError,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.handleUIEvent(ResetPasswordScreenUIEvent.EnteredEmail(it)) }
        )

        VerticalSpacer(heightDp = 40)

        BlueButton(
            buttonText = stringResource(id = R.string.next),
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