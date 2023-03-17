package com.example.movieapp.data.remote.dto.movie_image

import com.example.movieapp.domain.model.image.MovieImage

data class MovieImageDTO(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
) {

    fun toMovieImage(): MovieImage {
        return MovieImage(
            backdrops.map {
                it.file_path
            }
        )
    }
}