package com.example.movieapp.data.remote.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.util.constants.PagerConstants
import retrofit2.HttpException
import java.io.IOException

class RecommendedMoviesDataSource(
    private val repository: MovieRepository,
    private val movieId: Int,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val page = params.key ?: PagerConstants.INITIAL_PAGE_NUMBER

            val result =
                repository.getRecommendedMovies(
                    id = movieId,
                    page = page,
                    apiKey = BuildConfig.MOVIE_DB_API_KEY
                )

            if (result is Resource.Success) {

                val nextKey = if (result.data.page < PagerConstants.MAX_PAGE_NUMBER_1000)
                    result.data.page + 1 else null

                return LoadResult.Page(
                    data = result.data.results,
                    prevKey = null,
                    nextKey = nextKey
                )
            }

            throw Exception()

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}