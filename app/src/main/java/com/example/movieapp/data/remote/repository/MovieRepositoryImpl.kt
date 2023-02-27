package com.example.movieapp.data.remote.repository

import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.domain.datasource.RemoteMovieDS
import com.example.movieapp.domain.model.UpcomingMovie
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.util.safeRequest
import com.example.movieapp.util.safeRequestMapper
import kotlinx.coroutines.CoroutineDispatcher

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteMovieDS,
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

    override suspend fun getMovieDetail(id: Int, apiKey: String): Resource<MovieDetailDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getMovieDetail(id, apiKey)
        }
    }

    override suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Resource<RecommendedMovies> {
        println(remoteDataSource.getRecommendedMovies(id, page, apiKey).code())
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
}