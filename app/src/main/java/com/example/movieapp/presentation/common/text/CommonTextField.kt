package com.example.movieapp.presentation.common

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
    hasError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        isError = hasError,
        singleLine = true,
        maxLines = 1,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier,
        label = {
            Text(
                text = labelText,
                style = MaterialTheme.localFont.mediumH6,
                color = MaterialTheme.localColor.textWhite,
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.localColor.textGrey,
            errorBorderColor = MaterialTheme.localColor.secondaryRed,
            disabledBorderColor = MaterialTheme.localColor.primarySoft,
            focusedBorderColor = MaterialTheme.localColor.primarySoft,
            unfocusedBorderColor = MaterialTheme.localColor.primarySoft,
            errorTrailingIconColor = MaterialTheme.localColor.secondaryRed,
        )
    )
}

@Preview
@Composable
fun PreviewCommonTextField() {

    CommonTextField(text = "Example", onValueChange = {}, labelText = "Label", hasError = false)
}