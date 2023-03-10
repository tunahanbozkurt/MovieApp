package com.example.movieapp.presentation.home.screen.language

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import java.util.*

@Composable
fun ChangeLanguageScreen(
    viewModel: ChangeLanguageScreenVM = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current as Activity
    val selectedLanguage = viewModel.selectedLanguage.collectAsState().value

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        VerticalSpacer(heightDp = 24)

        SuggestedLanguages(
            selectedLanguage ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            val code = if (it == "en-US") "en" else "tr"
            viewModel.updateSelectedLanguage(it)
        }

        VerticalSpacer(heightDp = 24)

        OtherLanguages(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun SuggestedLanguages(
    selectedLanguage: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.localColor.primarySoft
            )
    ) {

        Text(
            text = stringResource(id = R.string.suggested_languages),
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textDarkGrey,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp)
        )

        VerticalSpacer(heightDp = 22)

        LanguageItem(
            language = stringResource(id = R.string.english),
            isChecked = androidx.compose.ui.text.intl.Locale.current.language == "en"
        ) {
            onClick.invoke("en-US")
        }

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(
            language = stringResource(id = R.string.turkish),
            isChecked = androidx.compose.ui.text.intl.Locale.current.language == "tr"
        ) {
            onClick.invoke("tr-TR")
        }

        VerticalSpacer(heightDp = 20)

    }
}

@Composable
fun OtherLanguages(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.localColor.primarySoft
            )
    ) {

        Text(
            text = stringResource(id = R.string.other_languages),
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textDarkGrey,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp)
        )

        VerticalSpacer(heightDp = 22)

        LanguageItem(language = stringResource(id = R.string.chinese), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(language = stringResource(id = R.string.croatian), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(language = stringResource(id = R.string.czech), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(language = stringResource(id = R.string.danish), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(language = stringResource(id = R.string.filipino), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

        Divider(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(1.dp), color = MaterialTheme.localColor.primarySoft
        )

        VerticalSpacer(heightDp = 20)

        LanguageItem(language = stringResource(id = R.string.finnish), isChecked = false) {}

        VerticalSpacer(heightDp = 20)

    }
}

@Composable
fun LanguageItem(
    language: String,
    isChecked: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = language,
            style = MaterialTheme.localFont.semiBoldH4,
            modifier = Modifier
                .padding(start = 13.dp)
                .weight(1f)
        )
        if (isChecked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkmark),
                tint = MaterialTheme.localColor.primaryBlueAccent,
                contentDescription = null
            )
        }
    }
}

fun changeLanguage(context: Activity, languageCode: String) {
    val newLocale = Locale(languageCode)
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val configuration = resources.configuration
    configuration.setLocale(newLocale)
    resources.updateConfiguration(configuration, displayMetrics)
    context.startActivity(Intent(context, MainActivity::class.java))
    context.finish()
}

@Preview
@Composable
fun PreviewChangeLanguageScreen() {
    ChangeLanguageScreen()
}