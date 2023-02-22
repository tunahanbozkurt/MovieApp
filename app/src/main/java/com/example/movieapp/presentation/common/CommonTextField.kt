package com.example.movieapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    text: String,
    labelText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.localColor.primaryDark,
            textColor = MaterialTheme.localColor.textGrey,
            errorBorderColor = MaterialTheme.localColor.secondaryRed,
            disabledBorderColor = MaterialTheme.localColor.primarySoft,
            focusedBorderColor = MaterialTheme.localColor.primarySoft,
            unfocusedBorderColor = MaterialTheme.localColor.primarySoft,
            errorTrailingIconColor = MaterialTheme.localColor.secondaryRed,
        ),
        isError = isError,
        singleLine = true,
        maxLines = 1,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        label = {
                Text(
                    text = labelText,
                    style = MaterialTheme.localFont.mediumH6,
                    color = MaterialTheme.localColor.textWhite,
                )
        },
        modifier = modifier.background(MaterialTheme.localColor.primaryDark),
        shape = RoundedCornerShape(24.dp),
    )
}

@Preview
@Composable
fun PreviewCommonTextField() {

    CommonTextField(text = "Example", onValueChange = {}, labelText = "Label")
}