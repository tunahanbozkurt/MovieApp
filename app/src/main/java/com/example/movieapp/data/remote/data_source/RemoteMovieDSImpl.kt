package com.example.movieapp.data.remote.data_source

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

    override suspend fun getTopRatedMovies(page: Int, apiKey: String): Response<PopularMoviesDTO> {
        return api.getTopRatedMovies(page, apiKey)
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

    override suspend fun multiSearchMovie(
        query: String,
        page: Int,
        apiKey: String
    ): Response<MultiSearchDTO> {
        return api.multiSearch(query, page, apiKey)
    }

    override suspend fun getTvShowDetail(id: Int, apiKey: String): Response<TvSeriesDetailDTO> {
        return api.getTvShowDetail(id, apiKey)
    }

    override suspend fun getTvShowCredits(id: Int, apiKey: String): Response<TvShowCreditsDTO> {
        return api.getTvShowCredits(id, apiKey)
    }

    override suspend fun getTvSeriesDetail(
        id: Int,
        apiKey: String,
        season: Int
    ): Response<TvSeasonDetailDTO> {
        return api.getTvSeasonDetails(tv_id = id, season_number = season, apiKey = apiKey)
    }

    override suspend fun getTvSeriesVideos(id: Int, apiKey: String): Response<TvSeriesVideosDTO> {
        return api.getSeriesVideos(id, apiKey)
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): Response<MovieVideosDTO> {
        return api.getMovieVideos(id, apiKey)
    }

    override suspend fun getMovieImages(id: Int, apiKey: String): Response<MovieImageDTO> {
        return api.getMovieImage(id, apiKey)
    }

    override suspend fun getTvSeriesImages(id: Int, apiKey: String): Response<MovieImageDTO> {
        return api.getTvSeriesImage(id, apiKey)
    }

    override suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Response<RecommendedMoviesDTO> {
        return api.getRecommended(id, page, apiKey)
    }
}