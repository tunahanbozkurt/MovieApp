package com.example.movieapp.domain.repository

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.tvseasondetail.TvSeasonDetailDTO
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Resource<PopularMovies>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Resource<List<UpcomingMovie>>
    suspend fun getMovieDetail(id: Int, apiKey: String): Resource<MovieDetail>
    suspend fun getMovieCredits(id: Int, apiKey: String): Resource<List<CastCrew>>
    suspend fun getTvShowDetail(id: Int, apiKey: String): Resource<MovieDetail>
    suspend fun getTvShowCredits(id: Int, apiKey: String): Resource<List<CastCrew>>
    suspend fun getTvSeasonDetail(id: Int, apiKey: String, season: Int): Resource<TvSeasonDetailDTO>
    suspend fun insertWish(model: MovieDetail, type: String)
    suspend fun insertMovieToRoom(model: MovieDetail)
    suspend fun getLatestSearchedMovie(): MovieEntity?
    fun getWishFlow(): Flow<List<WishEntity>>
    fun getLatestSearchedMovieFlow(): Flow<MovieEntity>

    suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Resource<RecommendedMovies>

    suspend fun getSearchedMovies(
        query: String,
        page: Int,
        apiKey: String
    ): Resource<MovieSearchDTO>

    suspend fun getMultiSearch(
        query: String,
        page: Int,
        apiKey: String
    ): Resource<MultiSearchDTO>

}