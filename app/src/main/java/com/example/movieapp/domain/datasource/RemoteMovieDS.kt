package com.example.movieapp.domain.datasource

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
import retrofit2.Response

interface RemoteMovieDS {
    /**
     * Fetches genres for the movies from the api.
     */
    suspend fun getAllMovieGenres(): Response<MovieGenreListDTO>

    /**
     * Fetches popular movies from the api.
     */
    suspend fun getPopularMovies(page: Int): Response<PopularMoviesDTO>

    /**
     * Fetches top-rated movies from the api.
     */
    suspend fun getTopRatedMovies(page: Int): Response<PopularMoviesDTO>

    /**
     * Fetches upcoming movies from the api.
     */
    suspend fun getUpcomingMovies(page: Int): Response<UpcomingMoviesDTO>

    /**
     * Fetches the movies that matches with given query parameter.
     */
    suspend fun searchMovie(query: String, page: Int): Response<MovieSearchDTO>

    /**
     * Fetches the movie's details by using movie's id from the api.
     */
    suspend fun getMovieDetail(id: Int): Response<MovieDetailDTO>

    /**
     * Fetches the movie's credits by using movie's id from the api.
     */
    suspend fun getMovieCredits(id: Int): Response<MovieCreditsDTO>

    /**
     * Fetches the movies and tv shows that match with given query parameter.
     */
    suspend fun multiSearchMovie(query: String, page: Int): Response<MultiSearchDTO>

    /**
     * Fetches the tv show's details by using id parameter from the api.
     */
    suspend fun getTvShowDetail(id: Int): Response<TvSeriesDetailDTO>

    /**
     * Fetches the tv show's credits by using tv show's id from the api
     */
    suspend fun getTvShowCredits(id: Int): Response<TvShowCreditsDTO>

    /**
     * Fetches the tv series's details by using id and season number parameters from the api.
     */
    suspend fun getTvSeriesDetail(id: Int, season: Int): Response<TvSeasonDetailDTO>

    /**
     * Fetches the tv series's videos by using id from the api.
     */
    suspend fun getTvSeriesVideos(id: Int): Response<TvSeriesVideosDTO>

    /**
     * Fetches the movie's videos by using id from the api.
     */
    suspend fun getMovieVideos(id: Int): Response<MovieVideosDTO>

    /**
     * Fetches the movie's images by using id from the api.
     */
    suspend fun getMovieImages(id: Int): Response<MovieImageDTO>

    /**
     * Fetches the tv series's images by using id from the api.
     */
    suspend fun getTvSeriesImages(id: Int): Response<MovieImageDTO>

    /**
     * Fetches the recommended movies by using id from the api.
     */
    suspend fun getRecommendedMovies(
        id: Int,
        page: Int
    ): Response<RecommendedMoviesDTO>
}