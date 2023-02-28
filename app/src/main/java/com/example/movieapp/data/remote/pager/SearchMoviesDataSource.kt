package com.example.movieapp.data.remote.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.util.encodeToUri

class SearchMoviesDataSource(
    private val query: String,
    private val repo: MovieRepository,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val page = params.key ?: 1

            val response = repo.getSearchedMovies(
                query = query.encodeToUri(),
                page = page,
                apiKey = BuildConfig.MOVIE_DB_API_KEY
            )

            if (response is Resource.Success && query.isNotEmpty()) {
                return LoadResult.Page(
                    data = response.data.results.map { it.toMovieItem() },
                    prevKey = null,
                    nextKey = if (response.data.page < 1000) response.data.page + 1 else null
                )
            }

            throw Exception()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}