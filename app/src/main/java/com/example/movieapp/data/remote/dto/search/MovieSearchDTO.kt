package com.example.movieapp.data.remote.dto.search

data class MovieSearchDTO(
    val page: Int,
    val results: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)