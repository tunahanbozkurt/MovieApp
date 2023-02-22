package com.example.movieapp.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueText
import com.example.movieapp.presentation.common.CenterAlignedText
import com.example.movieapp.presentation.onboarding.common.BlueButton
import com.example.movieapp.presentation.splash_screen.SplashView
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun AuthenticationScreen() {

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
        Spacer(modifier = Modifier.height(8.dp))

        CenterAlignedText(
            text = context.getString(R.string.auth_msg),
            style = MaterialTheme.localFont.semiBoldH5,
            textColor = MaterialTheme.localColor.textGrey,
            modifier = Modifier.width(200.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        
        BlueButton(
            buttonText = context.getString(R.string.sign_up),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            /*TODO Sign Up*/
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row {
            Text(
                text = context.getString(R.string.already_have_account),
                color = MaterialTheme.localColor.textGrey
            )
            Spacer(Modifier.width(4.dp))
            BlueText(text = context.getString(R.string.login))
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextWithDivider(text = context.getString(R.string.signup_with))

        Spacer(modifier = Modifier.height(40.dp))

        Row {
            Image(painterResource(id = R.drawable.ic_google), null)
            Spacer(modifier = Modifier.width(24.dp))
            Image(painter = painterResource(id = R.drawable.ic_apple), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
            Image(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = null)
        }

    }
}

@Preview
@Composable
fun PrevAuthenticationScreen() {
    AuthenticationScreen()
}