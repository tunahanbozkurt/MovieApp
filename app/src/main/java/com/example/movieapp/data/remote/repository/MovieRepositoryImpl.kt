package com.example.movieapp.data.remote.repository

import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.domain.RemoteMovieDS
import com.example.movieapp.domain.model.PopularMovie
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

    override suspend fun getPopularMovies(page: Int, apiKey: String): Resource<List<PopularMovie>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getPopularMovies(page, apiKey) },
            mapper = { mapperDto ->
                mapperDto.results.map { dto ->
                    dto.toPopularMovie()
                }
            }
        )
    }

    override suspend fun getPopularMoviesForPaging(
        page: Int,
        apiKey: String
    ): Resource<PopularMoviesDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getPopularMovies(page, apiKey)
        }
    }
}