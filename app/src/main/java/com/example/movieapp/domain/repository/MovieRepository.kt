package com.example.movieapp.domain.repository

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.movieVideo.MovieVideosDTO
import com.example.movieapp.data.remote.dto.movie_image.MovieImageDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.seriesVideos.TvSeriesVideosDTO
import com.example.movieapp.data.remote.dto.tvseasondetail.TvSeasonDetailDTO
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    /**
     * Provides the popular movies with the result status.
     */
    suspend fun getPopularMovies(page: Int): Resource<PopularMovies>

    /**
     * Provides the top-rated movies with the result status.
     */
    suspend fun getTopRatedMovies(page: Int): Resource<PopularMovies>

    /**
     * Provides the upcoming movies with the result status.
     */
    suspend fun getUpcomingMovies(page: Int): Resource<List<UpcomingMovie>>

    /**
     * Provides the movie's details by using id with the result status.
     */
    suspend fun getMovieDetail(id: Int): Resource<MovieDetail>

    /**
     * Provides the tv show's details by using id with the result status.
     */
    suspend fun getTvShowDetail(id: Int): Resource<MovieDetail>

    /**
     * Provides the movie's image urls by using id with the result status.
     */
    suspend fun getMovieImages(id: Int): Resource<MovieImageDTO>

    /**
     * Provides the tv series's image urls by using id with the result status.
     */
    suspend fun getTvSeriesImages(id: Int): Resource<MovieImageDTO>

    /**
     * Provides the movie's credits by using id with the result status.
     */
    suspend fun getMovieCredits(id: Int): Resource<List<CastCrew>>

    /**
     * Provides the tv show's credits by using id with the result status.
     */
    suspend fun getTvShowCredits(id: Int): Resource<List<CastCrew>>

    /**
     * Inserts the movie to the local database.
     */
    suspend fun insertMovieToRoom(model: MovieDetail)

    /**
     * Deletes the wish from the local database.
     */
    suspend fun deleteWish(entity: WishEntity)

    /**
     * Provides the latest searched movie or tv series from the local database.
     */
    suspend fun getLatestSearchedMovieOrSeries(): MovieEntity?

    /**
     * Provides the latest searched movie or tv series as a flow from the local database.
     */
    fun getLatestSearchedMovieOrSeriesAsFlow(): Flow<MovieEntity>

    /**
     * Provides the movie's genres with the result status.
     */
    suspend fun getAllMovieGenres(): Resource<MovieGenreListDTO>

    /**
     * Provides the tv season's details by using id and season number parameter with the result status.
     */
    suspend fun getTvSeasonDetail(id: Int, season: Int): Resource<TvSeasonDetailDTO>

    /**
     * Inserts the wish to the local database.
     */
    suspend fun insertWish(model: MovieDetail, type: String)

    /**
     * Provides the movie's videos by using id with the result status.
     */
    suspend fun getMovieVideos(id: Int): Resource<MovieVideosDTO>

    /**
     * Provides the tv series's videos by using id with the result status.
     */
    suspend fun getSeriesVideos(id: Int): Resource<TvSeriesVideosDTO>

    /**
     * Provides the wishes as flow from the local database.
     */
    fun getWishFlow(): Flow<List<WishEntity>>

    /**
     * Provides the recommended movies by using id with the result status.
     */
    suspend fun getRecommendedMovies(
        id: Int,
        page: Int
    ): Resource<RecommendedMovies>

    /**
     * Provides the searched movies by using query parameter with the result status.
     */
    suspend fun getSearchedMovies(
        query: String,
        page: Int
    ): Resource<MovieSearchDTO>

    /**
     * Provides the searched movies or tv series by using query parameter with the result status.
     */
    suspend fun getMultiSearch(
        query: String,
        page: Int
    ): Resource<MultiSearchDTO>
}