package com.example.movieapp.data.remote.dto.multiSearch


data class MultiSearchDTO(
    val page: Int,
    val results: List<MultiSearchResult>,
    val total_pages: Int,
    val total_results: Int
)