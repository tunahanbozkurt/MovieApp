package com.example.movieapp.presentation.auth.base

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.presentation.auth.TextWithDivider
import com.example.movieapp.presentation.common.*
import com.example.movieapp.presentation.navigation.AuthenticationScreen
import com.example.movieapp.presentation.splash_screen.SplashView
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.showToast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthenticationScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {

    val context = LocalContext.current
    val googleAuthLauncher = rememberFirebaseAuthLauncher(viewModel)

    val callbackManager = remember {
        CallbackManager.Factory.create()
    }

    DisposableEffect(Unit) {
        registerFacebookCallback(context, callbackManager, viewModel)
        onDispose {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
            navigate.invoke(AuthenticationScreen.SignUp.route)
        }

        VerticalSpacer(height = 16)

        Row {
            Text(
                text = context.getString(R.string.already_have_account),
                color = MaterialTheme.localColor.textGrey
            )
            Spacer(Modifier.width(4.dp))

            BlueText(text = context.getString(R.string.login), modifier = Modifier.clickable {
                navigate.invoke(AuthenticationScreen.Login.route)
            })
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
                    googleAuthLauncher.launch(googleSignInClient.signInIntent)
                }
            )
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_apple)
            Spacer(modifier = Modifier.width(24.dp))
            SocialMediaIcon(resId = R.drawable.ic_facebook, modifier = Modifier.clickable {
                LoginManager.getInstance().logIn(
                    context as ActivityResultRegistryOwner,
                    callbackManager,
                    listOf("public_profile")
                )
            })
        }
    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    viewModel: AuthenticationScreenVM
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        val account = task.getResult(ApiException::class.java)
        viewModel.signInWithGoogle(account)
    }
}

fun registerFacebookCallback(
    context: Context,
    callbackManager: CallbackManager,
    viewModel: AuthenticationScreenVM
) {
    LoginManager.getInstance()
        .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                viewModel.signInWithFacebook(result.accessToken)
            }

            override fun onCancel() {
                // doesn't require implementation
            }
            override fun onError(error: FacebookException) {
                context.showToast("Something went wrong try again later")
            }
        })
}

@Preview
@Composable
fun PrevAuthenticationScreen() {
    AuthenticationScreen {}
}