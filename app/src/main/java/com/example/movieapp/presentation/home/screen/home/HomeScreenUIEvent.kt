package com.example.movieapp.presentation.home.screen.home


sealed class HomeScreenUIEvent {
    data class Search(val query: String) : HomeScreenUIEvent()
    object SeeAll : HomeScreenUIEvent()
}
