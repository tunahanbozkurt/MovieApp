package com.example.movieapp.presentation.home.screen.wish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val wishFlow = repository.getWishFlow()

    fun deleteWish(entity: WishEntity) {
        viewModelScope.launch {
            repository.deleteWish(entity)
        }
    }
}
