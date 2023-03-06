package com.example.movieapp.data.remote.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import com.example.movieapp.util.constants.Pager
import com.example.movieapp.util.extensions.encodeToUri
import retrofit2.HttpException
import java.io.IOException

class SearchMoviesDataSource(
    private val query: String,
    private val repo: MovieRepository,
) : PagingSource<Int, MultiSearchResult>() {

    override fun getRefreshKey(state: PagingState<Int, MultiSearchResult>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MultiSearchResult> {
        return try {
            val page = params.key ?: Pager.INITIAL_PAGE_NUMBER

            val result = repo.getMultiSearch(
                query = query.encodeToUri(),
                page = page,
                apiKey = BuildConfig.MOVIE_DB_API_KEY
            )

            if (result is Resource.Success && query.isNotEmpty()) {

                val nextKey = if (result.data.page < Pager.MAX_PAGE_NUMBER_1000)
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

