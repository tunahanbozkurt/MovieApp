package com.example.movieapp.data.remote.dataSource

import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dto.credits.MovieCreditsDTO
import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.recommended.RecommendedMoviesDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
import com.example.movieapp.domain.datasource.RemoteMovieDS
import retrofit2.Response

class RemoteMovieDSImpl(
    private val api: MovieAPI
) : RemoteMovieDS {

    override suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO> {
        return api.getAllMovieGenres(apiKey)
    }

    override suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO> {
        return api.getPopularMovies(page = page, apiKey = apiKey)
    }

    override suspend fun getUpcomingMovies(page: Int, apiKey: String): Response<UpcomingMoviesDTO> {
        return api.getUpcoming(page, apiKey)
    }

    override suspend fun searchMovie(
        query: String,
        page: Int,
        apiKey: String
    ): Response<MovieSearchDTO> {
        return api.searchMovie(query, page, apiKey)
    }

    override suspend fun getMovieDetail(id: Int, apiKey: String): Response<MovieDetailDTO> {
        return api.getDetail(id, apiKey)
    }

    override suspend fun getMovieCredits(id: Int, apiKey: String): Response<MovieCreditsDTO> {
        return api.getMovieCredits(id, apiKey)
    }

    override suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Response<RecommendedMoviesDTO> {
        return api.getRecommended(id, page, apiKey)
    }
}