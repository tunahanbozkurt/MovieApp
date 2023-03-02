package com.example.movieapp.data.repository

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.tvseasondetail.TvSeasonDetailDTO
import com.example.movieapp.domain.datasource.LocalDataSource
import com.example.movieapp.domain.datasource.RemoteMovieDS
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.util.safeRequest
import com.example.movieapp.util.safeRequestMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteMovieDS,
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {

    override suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreListDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getAllMovieGenres(apiKey)
        }
    }

    override suspend fun getPopularMovies(page: Int, apiKey: String): Resource<PopularMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getPopularMovies(page, apiKey) },
            mapper = { mapperDto ->
                mapperDto.toPopularMovies()
            }
        )
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        apiKey: String
    ): Resource<List<UpcomingMovie>> {
        return safeRequestMapper(ioDispatcher,
            execute = {
                remoteDataSource.getUpcomingMovies(page, apiKey)
            },
            mapper = { mapperDto ->
                mapperDto.results.map { upcomingMovieResult ->
                    upcomingMovieResult.toUpcomingMovie()
                }
            })
    }

    override suspend fun getMovieDetail(id: Int, apiKey: String): Resource<MovieDetail> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getMovieDetail(id, apiKey) },
            mapper = { it.toMovieDetail() }
        )
    }

    override suspend fun getMovieCredits(id: Int, apiKey: String): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getMovieCredits(id, apiKey)
            },
            mapper = { mapperDto ->
                val casts = mapperDto.cast.map { it.toCastCrew() }
                val crews = mapperDto.crew.map { it.toCastCrew() }
                return@safeRequestMapper casts.plus(crews)
            }
        )
    }

    override suspend fun getTvShowDetail(id: Int, apiKey: String): Resource<MovieDetail> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvShowDetail(id, apiKey)
            },
            mapper = {
                it.toMovieDetail()
            }
        )
    }

    override suspend fun getTvShowCredits(id: Int, apiKey: String): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvShowCredits(id, apiKey)
            },
            mapper = {
                it.crew.map {
                    it.toCastCrew()
                }
            }
        )
    }

    override suspend fun getTvSeasonDetail(
        id: Int,
        apiKey: String,
        season: Int
    ): Resource<TvSeasonDetailDTO> {
        return safeRequest(
            ioDispatcher
        ) {
            remoteDataSource.getTvSeriesDetail(id = id, apiKey = apiKey, season = season)
        }
    }

    override suspend fun insertMovieToRoom(model: MovieDetail) {
        withContext(ioDispatcher) {
            localDataSource.insertMovie(model.toMovieEntity())
        }
    }

    override fun getLatestSearchedMovieFlow(): Flow<MovieEntity> {
        return localDataSource.getMovieFlow().flowOn(ioDispatcher)
    }

    override suspend fun getLatestSearchedMovie(): MovieEntity? {
        return localDataSource.getMovie()
    }

    override suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Resource<RecommendedMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getRecommendedMovies(id, page, apiKey)
            },
            mapper = {
                it.toRecommendedMovies()
            }
        )
    }

    override suspend fun getSearchedMovies(
        query: String,
        page: Int,
        apiKey: String
    ): Resource<MovieSearchDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.searchMovie(query, page, apiKey)
        }
    }

    override suspend fun getMultiSearch(
        query: String,
        page: Int,
        apiKey: String
    ): Resource<MultiSearchDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.multiSearchMovie(query, page, apiKey)
        }
    }
}