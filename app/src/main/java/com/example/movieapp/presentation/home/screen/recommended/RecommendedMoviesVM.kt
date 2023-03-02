package com.example.movieapp.presentation.home.screen.recommended

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.remote.pager.RecommendedMoviesDataSource
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecommendedMoviesVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _pagerFlow: MutableStateFlow<Flow<PagingData<MovieItem>>> =
        MutableStateFlow(emptyFlow())
    val pagerFlow: StateFlow<Flow<PagingData<MovieItem>>> = _pagerFlow

    fun setId(movieId: Int) {
        _pagerFlow.update {
            Pager(
                config = PagingConfig(20)
            ) {
                RecommendedMoviesDataSource(movieId = movieId, repository = repository)
            }.flow.cachedIn(viewModelScope)
        }
    }
}
