package com.example.movieapp.presentation.home.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.button.LogOutButton
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.card.PremiumMemberCard
import com.example.movieapp.presentation.home.elements.card.ProfileCard
import com.example.movieapp.presentation.home.elements.card.SettingsCardGroup
import com.example.movieapp.presentation.home.elements.card.SignOutCard
import com.example.movieapp.presentation.navigation.Graph
import com.example.movieapp.presentation.navigation.HomeScreen
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.setFalse
import com.example.movieapp.util.extensions.setTrue
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skydoves.cloudy.Cloudy

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {

    val currentUser by remember {
        mutableStateOf(Firebase.auth.currentUser)
    }
    val showDialog = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val scrollState = rememberScrollState()
    val imgPath = viewModel.getImagePath()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColor.primaryDark)
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState)
    ) {

        VerticalSpacer(heightDp = 8)

        Text(stringResource(id = R.string.profile), style = MaterialTheme.localFont.semiBoldH4)

        VerticalSpacer(heightDp = 24)

        ProfileCard(
            imgPath = imgPath,
            displayName = currentUser?.displayName ?: "",
            email = currentUser?.email ?: "",
            iconResId = R.drawable.ic_edit
        ) {
            navigate.invoke(HomeScreen.EditProfile.route)
        }

        VerticalSpacer(heightDp = 24)

        PremiumMemberCard(
            modifier = Modifier.fillMaxWidth()
        )

        VerticalSpacer(heightDp = 24)

        SettingsCardGroup(
            title = stringResource(id = R.string.account),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(
                    stringResource(id = R.string.member),
                    R.drawable.ic_profile,
                    MaterialTheme.localColor.primaryBlueAccent
                ),
                SettingModel(stringResource(id = R.string.change_password), R.drawable.ic_padlock),
            )
        ) {}

        VerticalSpacer(heightDp = 20)

        SettingsCardGroup(
            title = stringResource(id = R.string.general),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(
                    stringResource(id = R.string.notification),
                    R.drawable.ic_notification
                ),
                SettingModel(stringResource(id = R.string.language), R.drawable.ic_globe),
                SettingModel(stringResource(id = R.string.country), R.drawable.ic_flag),
                SettingModel(stringResource(id = R.string.clear_cache), R.drawable.ic_trash)
            )
        ) {
            when (it) {
                "Notification" -> {
                    navigate.invoke(HomeScreen.Notification.route)
                }
            }
        }

        VerticalSpacer(heightDp = 20)

        SettingsCardGroup(
            title = stringResource(id = R.string.more),
            modifier = Modifier.fillMaxWidth(),
            settings = listOf(
                SettingModel(stringResource(id = R.string.policies), R.drawable.ic_shield),
                SettingModel(stringResource(id = R.string.help), R.drawable.ic_question),
                SettingModel(stringResource(id = R.string.about_us), R.drawable.ic_alert)
            )
        ) {
            when (it) {
                "Legal and Policies" -> {
                    navigate.invoke(HomeScreen.PrivacyPolicy.route)
                }
            }
        }

        VerticalSpacer(heightDp = 40)

        LogOutButton(
            modifier = Modifier.fillMaxWidth()
        ) {
            showDialog.setTrue()
        }
    }

    if (showDialog.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { showDialog.setFalse() }
        ) {
            Cloudy(radius = 25, modifier = Modifier.fillMaxSize()) {}
            SignOutCard(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(24.dp),
                onCancel = {
                    showDialog.setFalse()
                },
                onLogOut = {
                    Firebase.auth.signOut()
                    navigate.invoke(Graph.AUTHENTICATION)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {

}