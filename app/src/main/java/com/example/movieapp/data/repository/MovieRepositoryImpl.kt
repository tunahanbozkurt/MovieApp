package com.example.movieapp.data.repository

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.domain.datasource.LocalDataSource
import com.example.movieapp.domain.datasource.RemoteMovieDS
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.detail.TvSeasonDetail
import com.example.movieapp.domain.model.image.MovieImage
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.domain.model.video.Videos
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

    override suspend fun getAllMovieGenres(): Resource<MovieGenreListDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getAllMovieGenres()
        }
    }

    override suspend fun getPopularMovies(page: Int): Resource<PopularMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getPopularMovies(page) },
            mapper = { mapperDto ->
                mapperDto.toPopularMovies()
            }
        )
    }

    override suspend fun getTopRatedMovies(page: Int): Resource<PopularMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getTopRatedMovies(page) },
            mapper = { mapperDto ->
                mapperDto.toPopularMovies()
            }
        )
    }

    override suspend fun getUpcomingMovies(
        page: Int
    ): Resource<List<UpcomingMovie>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getUpcomingMovies(page)
            },
            mapper = { mapperDto ->
                mapperDto.results.map { upcomingMovieResult ->
                    upcomingMovieResult.toUpcomingMovie()
                }
            })
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetail> {
        return safeRequestMapper(
            ioDispatcher,
            execute = { remoteDataSource.getMovieDetail(id) },
            mapper = { movieDetailDto ->
                movieDetailDto.toMovieDetail()
            }
        )
    }

    override suspend fun getMovieCredits(id: Int): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getMovieCredits(id)
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

    override suspend fun getTvShowDetail(id: Int): Resource<MovieDetail> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvShowDetail(id)
            },
            mapper = { tvSeriesDetailDto ->
                tvSeriesDetailDto.toMovieDetail()
            }
        )
    }

    override suspend fun getMovieImages(id: Int): Resource<MovieImage> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getMovieImages(id)
            }
        ) {
            it.toMovieImage()
        }
    }

    override suspend fun getTvSeriesImages(id: Int): Resource<MovieImage> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvSeriesImages(id)
            }
        ) {
            it.toMovieImage()
        }
    }

    override suspend fun getTvShowCredits(id: Int): Resource<List<CastCrew>> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvShowCredits(id)
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
        season: Int
    ): Resource<TvSeasonDetail> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvSeriesDetail(
                    id = id,
                    season = season
                )
            }
        ) {
            it.toTvSeasonDetail()
        }
    }

    override suspend fun insertWish(model: MovieDetail, type: String) {
        safeQuery(ioDispatcher) {
            localDataSource.insertWish(model.toWishEntity(type))
        }
    }

    override suspend fun getMovieVideos(id: Int): Resource<Videos> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getMovieVideos(id)
            }
        ) {
            it.toVideos()
        }
    }

    override suspend fun getSeriesVideos(id: Int): Resource<Videos> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getTvSeriesVideos(id)
            }
        ) {
            it.toVideos()
        }
    }

    override fun getWishFlow(): Flow<List<WishEntity>> {
        return localDataSource.getWishFlow().flowOn(ioDispatcher)
    }

    override suspend fun insertMovieToRoom(model: MovieDetail, type: String) {
        safeQuery(ioDispatcher) {
            localDataSource.insertMovie(model.toMovieEntity(type))
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
        page: Int
    ): Resource<RecommendedMovies> {
        return safeRequestMapper(
            ioDispatcher,
            execute = {
                remoteDataSource.getRecommendedMovies(id, page)
            },
            mapper = { recommendedMoviesDto ->
                recommendedMoviesDto.toRecommendedMovies()
            }
        )
    }

    override suspend fun getSearchedMovies(
        query: String,
        page: Int
    ): Resource<MovieSearchDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.searchMovie(query, page)
        }
    }

    override suspend fun getMultiSearch(
        query: String,
        page: Int
    ): Resource<MultiSearchDTO> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.multiSearchMovie(query, page)
        }
    }

    override suspend fun deleteWish(entity: WishEntity) {
        safeQuery(ioDispatcher) {
            localDataSource.deleteWish(entity)
        }
    }
}