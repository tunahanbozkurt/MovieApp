package com.example.movieapp.data.remote.dto.movie_image

data class MovieImageDTO(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
)