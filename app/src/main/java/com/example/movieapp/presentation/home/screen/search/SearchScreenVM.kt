package com.example.movieapp.presentation.home.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.presentation.common.model.SearchFieldState
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

    private val _searchField: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState())
    val searchField: StateFlow<SearchFieldState> = _searchField.asStateFlow()

    private val _recommendedMovies: MutableStateFlow<List<MovieItem>> = MutableStateFlow(listOf())
    val recommendedMovies: StateFlow<List<MovieItem>> = _recommendedMovies.asStateFlow()

    init {
        getRecommendedMovies()
    }

    fun handleUIEvent(event: SearchScreenUIEvent) {
        when (event) {
            is SearchScreenUIEvent.EnteredSearchQuery -> {
                _searchField.update {
                    it.copy(
                        query = event.query,
                        isHintVisible = event.query.isEmpty()
                    )
                }
            }
        }
    }

    private fun getRecommendedMovies() {
        viewModelScope.launch {
            val response = repository
                .getRecommendedMovies(id = 87827, 1, apiKey = BuildConfig.MOVIE_DB_API_KEY)

            response.onSuccess { recommendedMovies ->
                println(recommendedMovies.data)
                _recommendedMovies.update { recommendedMovies.data.results }
            }
        }
    }
}