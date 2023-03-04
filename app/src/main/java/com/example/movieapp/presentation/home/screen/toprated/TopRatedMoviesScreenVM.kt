package com.example.movieapp.presentation.home.screen.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.data.remote.pager.TopRatedMoviesDataSource
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val pager = Pager(
        config = PagingConfig(20)
    ) {
        TopRatedMoviesDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}