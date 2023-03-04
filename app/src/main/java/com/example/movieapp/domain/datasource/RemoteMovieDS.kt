package com.example.movieapp.domain.datasource

import com.example.movieapp.data.remote.dto.credits.MovieCreditsDTO
import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.movieVideo.MovieVideosDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.recommended.RecommendedMoviesDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.seriesVideos.TvSeriesVideosDTO
import com.example.movieapp.data.remote.dto.seriesdetail.TvSeriesDetailDTO
import com.example.movieapp.data.remote.dto.tvCredits.TvShowCreditsDTO
import com.example.movieapp.data.remote.dto.tvseasondetail.TvSeasonDetailDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
import retrofit2.Response

interface RemoteMovieDS {

    suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO>
    suspend fun getTopRatedMovies(page: Int, apiKey: String): Response<PopularMoviesDTO>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Response<UpcomingMoviesDTO>
    suspend fun searchMovie(query: String, page: Int, apiKey: String): Response<MovieSearchDTO>
    suspend fun getMovieDetail(id: Int, apiKey: String): Response<MovieDetailDTO>
    suspend fun getMovieCredits(id: Int, apiKey: String): Response<MovieCreditsDTO>
    suspend fun multiSearchMovie(query: String, page: Int, apiKey: String): Response<MultiSearchDTO>
    suspend fun getTvShowDetail(id: Int, apiKey: String): Response<TvSeriesDetailDTO>
    suspend fun getTvShowCredits(id: Int, apiKey: String): Response<TvShowCreditsDTO>
    suspend fun getTvSeriesDetail(id: Int, apiKey: String, season: Int): Response<TvSeasonDetailDTO>
    suspend fun getTvSeriesMovies(id: Int, apiKey: String): Response<TvSeriesVideosDTO>
    suspend fun getMovieVideos(id: Int, apiKey: String): Response<MovieVideosDTO>
    suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Response<RecommendedMoviesDTO>
}