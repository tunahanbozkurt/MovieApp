package com.example.movieapp.presentation.home.screen.trailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.image.MovieImage
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.presentation.home.screen.detail.ItemType
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
class TrailerScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _videoState: MutableStateFlow<String> = MutableStateFlow("")
    val videoState: StateFlow<String> = _videoState.asStateFlow()

    private val _castAndCrewState: MutableStateFlow<List<CastCrew>?> = MutableStateFlow(null)
    val castAndCrewState: StateFlow<List<CastCrew>?> = _castAndCrewState.asStateFlow()

    private val _movieDetailState: MutableStateFlow<MovieDetail> = MutableStateFlow(MovieDetail())
    val movieDetailState: StateFlow<MovieDetail> = _movieDetailState.asStateFlow()

    private val _imageUrls: MutableStateFlow<MovieImage?> = MutableStateFlow(null)
    val imageUrls: StateFlow<MovieImage?> = _imageUrls.asStateFlow()

    companion object {
        const val YOUTUBE = "YouTube"
    }

    fun loadData(id: Int, type: String) {
        if (type == ItemType.MOVIE.type) {
            setMovieData(id)
        } else {
            setTvSeriesData(id)
        }
    }

    private fun setTvSeriesData(id: Int) {
        viewModelScope.launch {

            val tvDetailResponse = async { repository.getTvShowDetail(id) }
            val castAndCrew = async { repository.getTvShowCredits(id) }
            val video = async { repository.getSeriesVideos(id) }
            val image = async { repository.getTvSeriesImages(id) }

            video.await().onSuccess { result ->
                val url = result.data.results.find {
                    it.site == YOUTUBE
                }?.key
                _videoState.update {
                    url ?: ""
                }
            }
            tvDetailResponse.await().onSuccess { result ->
                _movieDetailState.update {
                    result.data
                }
            }
            castAndCrew.await().onSuccess { result ->
                _castAndCrewState.update {
                    result.data
                }
            }
            image.await().onSuccess { result ->
                _imageUrls.update {
                    result.data
                }
            }
        }
    }

    private fun setMovieData(id: Int) {
        viewModelScope.launch {

            val movieDetailResponse = async { repository.getMovieDetail(id) }
            val castAndCrew = async { repository.getMovieCredits(id) }
            val video = async { repository.getMovieVideos(id) }
            val image = async { repository.getMovieImages(id) }

            video.await().onSuccess { result ->
                val url = result.data.results.find {
                    it.site == YOUTUBE
                }?.key

                _videoState.update {
                    url ?: ""
                }
            }
            movieDetailResponse.await().onSuccess { result ->
                _movieDetailState.update {
                    result.data
                }
            }
            castAndCrew.await().onSuccess { result ->
                _castAndCrewState.update {
                    result.data
                }
            }
            image.await().onSuccess { result ->
                _imageUrls.update {
                    result.data
                }
            }
        }
    }

    fun addWish(model: MovieDetail, type: String) {
        viewModelScope.launch {
            repository.insertWish(model, type)
        }
    }
}