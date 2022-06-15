package com.example.themoviedb.model

data class ResultMovie(
    var results: List<Movie>
)

data class ResultVideo(
    var results: List<MovieVideo>
)