package com.example.movieapp.presentation.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.presentation.common.*
import com.example.movieapp.presentation.splash_screen.SplashView
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationScreenVM = hiltViewModel(),
    navigate: () -> Unit
) {

    val context = LocalContext.current
    val authLauncher = rememberFirebaseAuthLauncher(
        onAuthComplete = {

        },
        onAuthError = {

        }
    )

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
            navigate.invoke()
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
            SocialMediaIcon(
                resId = R.drawable.ic_google,
                modifier = Modifier.clickable {
                    val gso =
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(BuildConfig.WEB_CLIENT_ID)
                            .requestEmail()
                            .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    authLauncher.launch(googleSignInClient.signInIntent)
                }
            )
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_apple)
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_facebook)
        }
    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}

@Preview
@Composable
fun PrevAuthenticationScreen() {
    AuthenticationScreen {}
}