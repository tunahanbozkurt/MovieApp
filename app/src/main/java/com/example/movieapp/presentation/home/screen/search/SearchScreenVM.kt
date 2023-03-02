package com.example.movieapp.presentation.home.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _recommendedMovies: MutableStateFlow<List<MovieItem>> = MutableStateFlow(listOf())
    val recommendedMovies: StateFlow<List<MovieItem>> = _recommendedMovies.asStateFlow()

    val latestSearchedMovie = repository.getLatestSearchedMovieFlow()

    companion object {
        const val BASE_MOVIE_ID = 634649
    }

    init {
        getRecommendedMovies(false)
    }

    private fun getRecommendedMovies(useBaseId: Boolean) {
        viewModelScope.launch {
            val id = repository.getLatestSearchedMovie()?.id
            val response = repository
                .getRecommendedMovies(
                    id = if (useBaseId) BASE_MOVIE_ID else id ?: BASE_MOVIE_ID,
                    1,
                    apiKey = BuildConfig.MOVIE_DB_API_KEY
                )

            response.onSuccess { recommendedMovies ->
                if (recommendedMovies.data.total_results == 0) {
                    getRecommendedMovies(true)
                } else {
                    _recommendedMovies.update { recommendedMovies.data.results }
                }
            }
        }
    }
}