package com.example.movieapp.data.remote

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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("genre/movie/list")
    suspend fun getAllMovieGenres(
        @Query("api_key") apiKey: String
    ): Response<MovieGenreListDTO>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailDTO>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<PopularMoviesDTO>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieSearchDTO>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<UpcomingMoviesDTO>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommended(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<RecommendedMoviesDTO>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieCreditsDTO>

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<MultiSearchDTO>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Response<TvSeriesDetailDTO>

    @GET("tv/{tv_id}/credits")
    suspend fun getTvShowCredits(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Response<TvShowCreditsDTO>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getTvSeasonDetails(
        @Path("tv_id") tv_id: Int,
        @Path("season_number") season_number: Int,
        @Query("api_key") apiKey: String,
    ): Response<TvSeasonDetailDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieVideosDTO>

    @GET("tv/{tv_id}/videos")
    suspend fun getSeriesVideos(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Response<TvSeriesVideosDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<PopularMoviesDTO>
}