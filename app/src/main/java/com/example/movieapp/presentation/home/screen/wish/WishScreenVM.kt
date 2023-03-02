package com.example.movieapp.presentation.home.screen.wish

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val wishFlow = repository.getWishFlow()
}
