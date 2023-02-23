package com.example.movieapp.domain.usecase.field

import com.example.movieapp.R
import com.example.movieapp.util.TaskResult

class CheckPasswordField {

    operator fun invoke(password: String): TaskResult {

        if (password.isEmpty()) {
            return TaskResult.Error(R.string.empty_field)
        }

        if (password.length < 6) {
            return TaskResult.Error(R.string.weak_password)
        }

        return TaskResult.Success
    }
}