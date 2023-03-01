package com.example.movieapp.presentation.home.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.repository.MovieRepository
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
class DetailScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieDetailState: MutableStateFlow<MovieDetail> = MutableStateFlow(MovieDetail())
    val movieDetailState: StateFlow<MovieDetail> = _movieDetailState.asStateFlow()

    private val _castAndCrewState: MutableStateFlow<List<CastCrew>?> = MutableStateFlow(null)
    val castAndCrewState: StateFlow<List<CastCrew>?> = _castAndCrewState.asStateFlow()

    fun loadData(movieId: Int) {
        viewModelScope.launch {
            val movieDetailResponse = async {
                repository.getMovieDetail(movieId, apiKey = BuildConfig.MOVIE_DB_API_KEY)
            }
            val castAndCrew = async {
                repository.getMovieCredits(movieId, apiKey = BuildConfig.MOVIE_DB_API_KEY)
            }
            movieDetailResponse.await().onSuccess { result ->
                updateLastSearchedMovie(result.data)
                _movieDetailState.update {
                    result.data
                }
            }
            castAndCrew.await().onSuccess { result ->
                _castAndCrewState.update {
                    result.data
                }
            }
        }
    }

    private fun updateLastSearchedMovie(model: MovieDetail) {
        viewModelScope.launch { repository.insertMovieToRoom(model) }
    }
}