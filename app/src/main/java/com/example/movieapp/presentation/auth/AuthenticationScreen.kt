package com.example.movieapp.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.*
import com.example.movieapp.presentation.splash_screen.SplashView
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationScreenVM = hiltViewModel()
) {

    val context = LocalContext.current


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.localColor.primaryDark)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

        SplashView(
            text = context.getString(R.string.cinemax),
            textColor = MaterialTheme.localColor.textWhite
        )

        VerticalSpacer(height = 8)

        CenterAlignedText(
            text = context.getString(R.string.auth_msg),
            style = MaterialTheme.localFont.semiBoldH5,
            textColor = MaterialTheme.localColor.textGrey,
            modifier = Modifier.width(200.dp)
        )

        VerticalSpacer(height = 64)

        BlueButton(
            buttonText = context.getString(R.string.sign_up),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            /*TODO Sign Up*/
        }

        VerticalSpacer(height = 16)

        Row {
            Text(
                text = context.getString(R.string.already_have_account),
                color = MaterialTheme.localColor.textGrey
            )
            Spacer(Modifier.width(4.dp))
            BlueText(text = context.getString(R.string.login))
        }

        VerticalSpacer(height = 32)

        TextWithDivider(text = context.getString(R.string.signup_with))

        VerticalSpacer(height = 40)


        Row {
            SocialMediaIcon(resId = R.drawable.ic_google, modifier = Modifier.clickable {})
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_apple)
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_facebook)
        }
    }
}

@Preview
@Composable
fun PrevAuthenticationScreen() {
    AuthenticationScreen()
}