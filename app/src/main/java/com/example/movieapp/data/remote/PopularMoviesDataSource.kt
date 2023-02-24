package com.example.movieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.dto.popular.PopularMovieDTO
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.util.Resource

class PopularMoviesDataSource(
    private val repo: MovieRepository
) : PagingSource<Int, PopularMovieDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovieDTO>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieDTO> {
        return try {
            val page = params.key ?: 1
            val response = repo.getPopularMoviesForPaging(page = page, apiKey = BuildConfig.MOVIE_DB_API_KEY)
            if (response is Resource.Success) {
                return LoadResult.Page(
                    data = response.data.results,
                    prevKey = null,
                    nextKey = if (response.data.page < 500) response.data.page + 1 else null
                )
            }
            throw Exception()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}