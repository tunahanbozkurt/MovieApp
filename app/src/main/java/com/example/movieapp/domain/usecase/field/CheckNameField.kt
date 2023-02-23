package com.example.movieapp.domain.usecase.field

import com.example.movieapp.R
import com.example.movieapp.util.TaskResult

class CheckNameField {

    operator fun invoke(name: String): TaskResult {

        if (name.isEmpty()) {
            return TaskResult.Error(R.string.empty_field)
        }

        if (name.length < 3) {
            return TaskResult.Error(R.string.short_name)
        }

        return TaskResult.Success
    }
}