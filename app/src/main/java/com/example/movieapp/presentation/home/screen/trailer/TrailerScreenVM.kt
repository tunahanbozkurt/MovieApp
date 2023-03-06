package com.example.movieapp.presentation.home.screen.trailer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.dto.movie_image.MovieImageDTO
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
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

    private val _videoDetailState: MutableStateFlow<MovieDetail> = MutableStateFlow(MovieDetail())
    val videoDetailState: StateFlow<MovieDetail> = _videoDetailState.asStateFlow()

    private val _castAndCrewState: MutableStateFlow<List<CastCrew>?> = MutableStateFlow(null)
    val castAndCrewState: StateFlow<List<CastCrew>?> = _castAndCrewState.asStateFlow()

    private val _movieDetailState: MutableStateFlow<MovieDetail> = MutableStateFlow(MovieDetail())
    val movieDetailState: StateFlow<MovieDetail> = _movieDetailState.asStateFlow()

    private val _imageUrls: MutableStateFlow<MovieImageDTO?> = MutableStateFlow(null)
    val imageUrls: StateFlow<MovieImageDTO?> = _imageUrls.asStateFlow()


    fun loadData(id: Int, type: String) {
        if (type == ItemType.MOVIE.type) {
            viewModelScope.launch {
                val movieDetailResponse = async {
                    repository.getMovieDetail(id, apiKey = BuildConfig.MOVIE_DB_API_KEY)
                }
                val castAndCrew = async {
                    repository.getMovieCredits(id, apiKey = BuildConfig.MOVIE_DB_API_KEY)
                }
                val video = async { repository.getMovieVideos(id, BuildConfig.MOVIE_DB_API_KEY) }

                val image = async { repository.getMovieImages(id, BuildConfig.MOVIE_DB_API_KEY) }

                video.await().onSuccess { result ->
                    val url = result.data.results.find {
                        it.site == "YouTube"
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
        } else {
            viewModelScope.launch {
                val tvDetailResponse =
                    async { repository.getTvShowDetail(id, BuildConfig.MOVIE_DB_API_KEY) }
                val castAndCrew =
                    async { repository.getTvShowCredits(id, BuildConfig.MOVIE_DB_API_KEY) }

                val video = async { repository.getSeriesVideos(id, BuildConfig.MOVIE_DB_API_KEY) }

                val image = async { repository.getTvSeriesImages(id, BuildConfig.MOVIE_DB_API_KEY) }

                video.await().onSuccess { result ->
                    val url = result.data.results.find {
                        it.site == "YouTube"
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
    }

    fun addWish(model: MovieDetail, type: String) {
        viewModelScope.launch {
            repository.insertWish(model, type)
        }
    }
}