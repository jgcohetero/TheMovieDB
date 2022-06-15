package com.example.themoviedb.model


// ArrayObject obtenido

data class ResultMovie(
    var results: List<Movie>
)

data class ResultVideo(
    var results: List<MovieVideo>
)