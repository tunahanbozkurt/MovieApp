package com.example.movieapp.domain.usecase.field

import com.example.movieapp.R
import com.example.movieapp.util.TaskResult
import com.example.movieapp.util.extensions.isValidEmail

class CheckEmailField {

    operator fun invoke(email: String): TaskResult {

        if (email.isEmpty()) {
            return TaskResult.Error(R.string.empty_field)
        }

        if (!email.isValidEmail()) {
            return TaskResult.Error(R.string.invalid_email_pattern)
        }

        return TaskResult.Success
    }
}