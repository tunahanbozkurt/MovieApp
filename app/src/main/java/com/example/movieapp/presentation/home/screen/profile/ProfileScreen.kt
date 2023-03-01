package com.example.movieapp.presentation.home.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.button.LogOutButton
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.ProfileCard
import com.example.movieapp.presentation.home.elements.SettingsCardGroup
import com.example.movieapp.ui.theme.localColor
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen() {

    val currentUser by remember {
        mutableStateOf(Firebase.auth.currentUser)
    }
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColor.primaryDark)
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState)
    ) {

        ProfileCard(
            displayName = currentUser?.displayName ?: "",
            email = currentUser?.email ?: "",
            iconResId = R.drawable.ic_edit
        )

        VerticalSpacer(heightDp = 24)

        SettingsCardGroup(
            title = stringResource(id = R.string.account),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(stringResource(id = R.string.member), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.change_password), R.drawable.ic_film),
            )
        )

        VerticalSpacer(heightDp = 20)

        SettingsCardGroup(
            title = stringResource(id = R.string.general),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(stringResource(id = R.string.language), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.country), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.clear_cache), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.notification), R.drawable.ic_film)
            )
        )

        VerticalSpacer(heightDp = 20)

        SettingsCardGroup(
            title = stringResource(id = R.string.more),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(stringResource(id = R.string.policies), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.help), R.drawable.ic_film),
                SettingModel(stringResource(id = R.string.about_us), R.drawable.ic_film)
            )
        )

        VerticalSpacer(heightDp = 40)

        LogOutButton(
            modifier = Modifier.fillMaxWidth()
        ) {
            /*TODO*/
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {

}