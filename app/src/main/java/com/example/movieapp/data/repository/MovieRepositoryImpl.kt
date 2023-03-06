package com.example.movieapp.data.repository

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.movieVideo.MovieVideosDTO
import com.example.movieapp.data.remote.dto.movie_image.MovieImageDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.seriesVideos.TvSeriesVideosDTO
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
import com.example.movieapp.util.extensions.safeQuery
import com.example.movieapp.util.extensions.safeRequest
import com.example.movieapp.util.extensions.safeRequestMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

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

    override suspend fun getTopRatedMovies(page: Int, apiKey: String): Resource<PopularMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getTopRatedMovies(page, apiKey) },
            mapper = { mapperDto ->
                mapperDto.toPopularMovies()
            }
        )
    }

    override suspend fun getUpcomingMovies(
        page: Int,
        apiKey: String
    ): Resource<List<UpcomingMovie>> {
        return safeRequestMapper(
            ioDispatcher,
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
            mapper = { movieDetailDto ->
                movieDetailDto.toMovieDetail()
            }
        )
    }

    override suspend fun getMovieCredits(id: Int, apiKey: String): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getMovieCredits(id, apiKey)
            },
            mapper = { mapperDto ->
                val casts = mapperDto.cast.map { castDto ->
                    castDto.toCastCrew()
                }
                val crews = mapperDto.crew.map { crewDto ->
                    crewDto.toCastCrew()
                }
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
            mapper = { tvSeriesDetailDto ->
                tvSeriesDetailDto.toMovieDetail()
            }
        )
    }

    override suspend fun getMovieImages(id: Int, apiKey: String): Resource<MovieImageDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getMovieImages(id, apiKey)
        }
    }

    override suspend fun getTvSeriesImages(id: Int, apiKey: String): Resource<MovieImageDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getTvSeriesImages(id, apiKey)
        }
    }

    override suspend fun getTvShowCredits(id: Int, apiKey: String): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvShowCredits(id, apiKey)
            },
            mapper = { tvShowCreditsDto ->
                tvShowCreditsDto.crew.map { crew ->
                    crew.toCastCrew()
                }
            }
        )
    }

    override suspend fun getTvSeasonDetail(
        id: Int,
        apiKey: String,
        season: Int
    ): Resource<TvSeasonDetailDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getTvSeriesDetail(
                id = id,
                apiKey = apiKey,
                season = season
            )
        }
    }

    override suspend fun insertWish(model: MovieDetail, type: String) {
        safeQuery(ioDispatcher) {
            localDataSource.insertWish(model.toWishEntity(type))
        }
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): Resource<MovieVideosDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getMovieVideos(id, apiKey)
        }
    }

    override suspend fun getSeriesVideos(id: Int, apiKey: String): Resource<TvSeriesVideosDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getTvSeriesVideos(id, apiKey)
        }
    }

    override fun getWishFlow(): Flow<List<WishEntity>> {
        return localDataSource.getWishFlow().flowOn(ioDispatcher)
    }

    override suspend fun insertMovieToRoom(model: MovieDetail) {
        safeQuery(ioDispatcher) {
            localDataSource.insertMovie(model.toMovieEntity())
        }
    }

    override fun getLatestSearchedMovieOrSeriesAsFlow(): Flow<MovieEntity> {
        return localDataSource.getMovieFlow().flowOn(ioDispatcher)
    }

    override suspend fun getLatestSearchedMovieOrSeries(): MovieEntity? {
        return safeQuery(ioDispatcher) {
            localDataSource.getMovie()
        }
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
            mapper = { recommendedMoviesDto ->
                recommendedMoviesDto.toRecommendedMovies()
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

    override suspend fun deleteWish(entity: WishEntity) {
        safeQuery(ioDispatcher) {
            localDataSource.deleteWish(entity)
        }
    }
}