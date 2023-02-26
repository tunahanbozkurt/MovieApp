package com.example.movieapp.presentation.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.data.remote.PopularMoviesDataSource
import com.example.movieapp.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MostPopularMoviesScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val pager = Pager(
        config = PagingConfig(20)
    ) {
        PopularMoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}