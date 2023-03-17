package com.example.movieapp.presentation.home.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.detail.TvSeasonDetail
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

    private val _seasonDetailState: MutableStateFlow<TvSeasonDetail?> = MutableStateFlow(null)
    val seasonDetailState: StateFlow<TvSeasonDetail?> = _seasonDetailState.asStateFlow()

    private val _castAndCrewState: MutableStateFlow<List<CastCrew>?> = MutableStateFlow(null)
    val castAndCrewState: StateFlow<List<CastCrew>?> = _castAndCrewState.asStateFlow()

    fun loadSeasonData(id: Int, seasonNumber: Int) {
        viewModelScope.launch {
            val detail = repository.getTvSeasonDetail(
                id = id,
                season = seasonNumber
            )
            detail.onSuccess { result ->
                _seasonDetailState.update {
                    result.data
                }
            }
        }
    }

    fun loadData(id: Int, type: String) {
        if (type == ItemType.MOVIE.type) {
            viewModelScope.launch {
                val movieDetailResponse = async {
                    repository.getMovieDetail(id)
                }
                val castAndCrew = async {
                    repository.getMovieCredits(id)
                }
                movieDetailResponse.await().onSuccess { result ->
                    updateLastSearchedMovie(result.data, type = type)
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
        } else {
            viewModelScope.launch {
                val tvDetailResponse =
                    async { repository.getTvShowDetail(id) }
                val castAndCrew =
                    async { repository.getTvShowCredits(id) }

                async { loadSeasonData(id, 1) }

                tvDetailResponse.await().onSuccess { result ->
                    updateLastSearchedMovie(model = result.data, type = type)
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
    }

    fun addWish(model: MovieDetail, type: String) {
        viewModelScope.launch {
            repository.insertWish(model, type)
        }
    }

    private fun updateLastSearchedMovie(model: MovieDetail, type: String) {
        viewModelScope.launch { repository.insertMovieToRoom(model, type) }
    }
}

enum class ItemType(val type: String) {
    SERIES("series"),
    MOVIE("movie"),
    PERSON("person")
}