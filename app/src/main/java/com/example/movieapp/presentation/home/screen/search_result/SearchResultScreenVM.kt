package com.example.movieapp.presentation.home.screen.search_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.remote.pager.SearchMoviesDataSource
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.presentation.common.model.SearchFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchResultScreenVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _pagerFlow: MutableStateFlow<Flow<PagingData<com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult>>> =
        MutableStateFlow(emptyFlow())
    val pagerFlow: StateFlow<Flow<PagingData<com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult>>> =
        _pagerFlow

    private val _searchField: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState())
    val searchField: StateFlow<SearchFieldState> = _searchField.asStateFlow()

    fun setQuery(query: String) {
        _searchField.update {
            it.copy(query = query, isHintVisible = it.query.isEmpty())
        }
        _pagerFlow.update {
            Pager(
                config = PagingConfig(20)
            ) {
                SearchMoviesDataSource(_searchField.value.query, repository)
            }.flow.cachedIn(viewModelScope)
        }
    }
}