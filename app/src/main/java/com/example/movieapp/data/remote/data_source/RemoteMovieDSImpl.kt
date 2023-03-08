package com.example.movieapp.data.remote.data_source

import android.content.SharedPreferences
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dto.credits.MovieCreditsDTO
import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.movieVideo.MovieVideosDTO
import com.example.movieapp.data.remote.dto.movie_image.MovieImageDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.recommended.RecommendedMoviesDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.seriesVideos.TvSeriesVideosDTO
import com.example.movieapp.data.remote.dto.seriesdetail.TvSeriesDetailDTO
import com.example.movieapp.data.remote.dto.tvCredits.TvShowCreditsDTO
import com.example.movieapp.data.remote.dto.tvseasondetail.TvSeasonDetailDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
import com.example.movieapp.domain.datasource.RemoteMovieDS
import com.example.movieapp.util.constants.SharedPref
import retrofit2.Response

class RemoteMovieDSImpl(
    private val api: MovieAPI,
    private val sharedPreferences: SharedPreferences
) : RemoteMovieDS {

    override suspend fun getAllMovieGenres(): Response<MovieGenreListDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getAllMovieGenres(language = language)
    }

    override suspend fun getPopularMovies(page: Int): Response<PopularMoviesDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getPopularMovies(page = page, language = language)
    }

    override suspend fun getTopRatedMovies(page: Int): Response<PopularMoviesDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getTopRatedMovies(page, language = language)
    }

    override suspend fun getUpcomingMovies(page: Int): Response<UpcomingMoviesDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getUpcoming(page, language = language)
    }

    override suspend fun searchMovie(
        query: String,
        page: Int
    ): Response<MovieSearchDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.searchMovie(query, page, language = language)
    }

    override suspend fun getMovieDetail(id: Int): Response<MovieDetailDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getDetail(id, language = language)
    }

    override suspend fun getMovieCredits(id: Int): Response<MovieCreditsDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getMovieCredits(id, language = language)
    }

    override suspend fun multiSearchMovie(
        query: String,
        page: Int
    ): Response<MultiSearchDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.multiSearch(query, page, language = language)
    }

    override suspend fun getTvShowDetail(id: Int): Response<TvSeriesDetailDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getTvShowDetail(id, language = language)
    }

    override suspend fun getTvShowCredits(id: Int): Response<TvShowCreditsDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getTvShowCredits(id, language = language)
    }

    override suspend fun getTvSeriesDetail(
        id: Int,
        season: Int
    ): Response<TvSeasonDetailDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getTvSeasonDetails(tv_id = id, season_number = season, language = language)
    }

    override suspend fun getTvSeriesVideos(id: Int): Response<TvSeriesVideosDTO> {
        return api.getSeriesVideos(id)
    }

    override suspend fun getMovieVideos(id: Int): Response<MovieVideosDTO> {
        return api.getMovieVideos(id)
    }

    override suspend fun getMovieImages(id: Int): Response<MovieImageDTO> {
        return api.getMovieImage(id)
    }

    override suspend fun getTvSeriesImages(id: Int): Response<MovieImageDTO> {
        return api.getTvSeriesImage(id)
    }

    override suspend fun getRecommendedMovies(
        id: Int,
        page: Int
    ): Response<RecommendedMoviesDTO> {
        val language =
            sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        return api.getRecommended(id, page, language = language)
    }
}