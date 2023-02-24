package com.example.movieapp.presentation.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.domain.model.PopularMovieList
import com.example.movieapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies: MutableStateFlow<PopularMovieList> = MutableStateFlow(PopularMovieList())
    val popularMovie: StateFlow<PopularMovieList> = _popularMovies.asStateFlow()

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            val response =
                movieRepository.getPopularMovies(page = 1, apiKey = BuildConfig.MOVIE_DB_API_KEY)

            response.onSuccess { resource ->
                _popularMovies.update {
                    it.copy(list = resource.data)
                }
            }

        }
    }
}