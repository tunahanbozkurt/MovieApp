package com.example.movieapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor

@Composable
fun PasswordField(
    password: String,
    isVisible: Boolean,
    hasError: Boolean,
    modifier: Modifier = Modifier,
    iconClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    CommonTextField(
        text = password,
        labelText = "Password",
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier,
        hasError = hasError,
        trailingIcon = {
            Icon(
                imageVector = if (isVisible) ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24) else ImageVector.vectorResource(
                    id = R.drawable.baseline_visibility_24
                ),
                contentDescription = null,
                tint = MaterialTheme.localColor.textGrey,
                modifier = Modifier.clickable { iconClick.invoke() }
            )
        },
        onValueChange = { onValueChange.invoke(it) }
    )
}