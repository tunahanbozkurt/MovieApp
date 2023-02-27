package com.example.movieapp.presentation.home.screen.search

sealed class SearchScreenUIEvent {
    data class EnteredSearchQuery(val query: String) : SearchScreenUIEvent()
}
