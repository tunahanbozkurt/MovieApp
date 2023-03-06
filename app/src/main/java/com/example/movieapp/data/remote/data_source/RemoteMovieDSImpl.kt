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

    override suspend fun getAllMovieGenres(): Response<MovieGenreListDTO> {
        return api.getAllMovieGenres()
    }

    override suspend fun getPopularMovies(page: Int): Response<PopularMoviesDTO> {
        return api.getPopularMovies(page = page)
    }

    override suspend fun getTopRatedMovies(page: Int): Response<PopularMoviesDTO> {
        return api.getTopRatedMovies(page)
    }

    override suspend fun getUpcomingMovies(page: Int): Response<UpcomingMoviesDTO> {
        return api.getUpcoming(page)
    }

    override suspend fun searchMovie(
        query: String,
        page: Int
    ): Response<MovieSearchDTO> {
        return api.searchMovie(query, page)
    }

    override suspend fun getMovieDetail(id: Int): Response<MovieDetailDTO> {
        return api.getDetail(id)
    }

    override suspend fun getMovieCredits(id: Int): Response<MovieCreditsDTO> {
        return api.getMovieCredits(id)
    }

    override suspend fun multiSearchMovie(
        query: String,
        page: Int
    ): Response<MultiSearchDTO> {
        return api.multiSearch(query, page)
    }

    override suspend fun getTvShowDetail(id: Int): Response<TvSeriesDetailDTO> {
        return api.getTvShowDetail(id)
    }

    override suspend fun getTvShowCredits(id: Int): Response<TvShowCreditsDTO> {
        return api.getTvShowCredits(id)
    }

    override suspend fun getTvSeriesDetail(
        id: Int,
        season: Int
    ): Response<TvSeasonDetailDTO> {
        return api.getTvSeasonDetails(tv_id = id, season_number = season)
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
        return api.getRecommended(id, page)
    }
}