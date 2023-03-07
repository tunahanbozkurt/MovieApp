package com.example.movieapp.presentation.home.screen.editprofile

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.model.ScreenEvent
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.CommonTextField
import com.example.movieapp.presentation.home.elements.EditProfileInfoSection
import com.example.movieapp.util.extensions.showToast
import java.io.File
import kotlin.random.Random


@Composable
fun EditProfileScreen(
    viewModel: EditProfileScreenVM = hiltViewModel()
) {
    val context = LocalContext.current
    val nameState = viewModel.nameFieldState.collectAsState().value
    val passwordState = viewModel.passwordFieldState.collectAsState().value
    val emailState = viewModel.emailFieldState.collectAsState().value
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val imgPath = viewModel.profileImageUri.collectAsState().value
    val scrollState = rememberScrollState()

    val pickImageLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val bitmap = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, uri)
                try {
                    val name = "profile_image_${Random.nextInt()}.png"
                    context.openFileOutput(name, Context.MODE_PRIVATE).use { fos ->
                        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fos)
                    }
                    val path = File(context.filesDir, name).absolutePath
                    viewModel.setProfileImagePath(path)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is ScreenEvent.Navigate -> {
                    onBackPressedDispatcher?.onBackPressed()
                }
                is ScreenEvent.ShowToast -> {
                    context.showToast(event.msg)
                }
                else -> {}
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
    ) {
        EditProfileInfoSection(
            imgPath = imgPath,
            name = viewModel.initDisplayName,
            email = viewModel.initEmail
        ) {
            pickImageLauncher.launch("image/*")
        }
        VerticalSpacer(heightDp = 40)
        CommonTextField(
            text = nameState.text,
            labelText = stringResource(id = R.string.full_name),
            hasError = false,
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredName(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = emailState.text,
            labelText = stringResource(id = R.string.email),
            hasError = false,
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredEmail(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = passwordState.password,
            labelText = stringResource(id = R.string.password),
            hasError = passwordState.hasError,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { viewModel.handleUIEvent(EditProfileScreenUIEvent.EnteredPassword(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 24)
        CommonTextField(
            text = stringResource(id = R.string.example_phone),
            labelText = stringResource(id = R.string.phone_number),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            hasError = false,
            readOnly = true,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 40)
        BlueButton(
            buttonText = stringResource(id = R.string.save_changes),
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.handleUIEvent(EditProfileScreenUIEvent.Save)
        }
        VerticalSpacer(heightDp = 20)
    }
}

@Preview
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}




