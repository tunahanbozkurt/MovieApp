package com.example.movieapp.presentation.home.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.presentation.common.model.SearchFieldState
import com.example.movieapp.util.extensions.convertToDate
import com.example.movieapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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

    private val _popularMovies: MutableStateFlow<PopularMovies> =
        MutableStateFlow(PopularMovies())
    val popularMovie: StateFlow<PopularMovies> = _popularMovies.asStateFlow()

    private val _topRatedMovies: MutableStateFlow<PopularMovies> =
        MutableStateFlow(PopularMovies())
    val topRatedMovies: StateFlow<PopularMovies> = _topRatedMovies.asStateFlow()

    private val _upcomingMovies: MutableStateFlow<List<UpcomingMovie>> =
        MutableStateFlow(listOf())
    val upcomingMovies: StateFlow<List<UpcomingMovie>> = _upcomingMovies.asStateFlow()

    private val _selectedGenre: MutableStateFlow<Genre> = MutableStateFlow(Genre(0, "All"))
    val selectedGenre: StateFlow<Genre> = _selectedGenre.asStateFlow()

    private val _searchFieldState: MutableStateFlow<SearchFieldState> = MutableStateFlow(
        SearchFieldState()
    )
    val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState.asStateFlow()

    init {
        loadPopularMovies()
        loadUpcomingMovies()
    }

    fun handleUIEvent(event: HomeScreenUIEvent) {
        when (event) {
            is HomeScreenUIEvent.Search -> {
                _searchFieldState.update { it.copy(query = event.query, isHintVisible = false) }
            }
            HomeScreenUIEvent.SeeAll -> {

            }
        }
    }

    fun setGenre(genre: Genre) {
        _selectedGenre.update { genre }
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            val popularResponse = async {
                movieRepository.getPopularMovies(page = 1, apiKey = BuildConfig.MOVIE_DB_API_KEY)
            }

            val topRatedResponse = async {
                movieRepository.getTopRatedMovies(page = 1, apiKey = BuildConfig.MOVIE_DB_API_KEY)
            }

            popularResponse.await().onSuccess { resource ->
                _popularMovies.update {
                    resource.data
                }
            }

            topRatedResponse.await().onSuccess { resource ->
                _topRatedMovies.update {
                    resource.data
                }
            }
        }
    }

    private fun loadUpcomingMovies() {
        viewModelScope.launch {
            val response =
                movieRepository.getUpcomingMovies(page = 1, apiKey = BuildConfig.MOVIE_DB_API_KEY)

            response.onSuccess { resource ->
                _upcomingMovies.update {
                    resource.data.subList(0, 3).map {
                        it.copy(releaseDate = it.releaseDate.convertToDate())
                    }
                }
            }
        }
    }
}