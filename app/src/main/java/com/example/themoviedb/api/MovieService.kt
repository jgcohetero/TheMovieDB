package com.example.themoviedb.api

import com.example.themoviedb.model.Movie
import com.example.themoviedb.model.MovieVideo
import com.example.themoviedb.model.ResultMovie
import com.example.themoviedb.model.ResultVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Metodos para obtener información de las películas

interface MovieService {

    @GET("{option}?api_key=10b603ec4b946df16959d577ebe2f890&language=en-US")
    fun getMovies(@Path("option") option: String) : Call<ResultMovie>

    @GET("{id}?api_key=10b603ec4b946df16959d577ebe2f890&language=en-US")
    fun getMovieInfo(@Path("id") id: String): Call<Movie>

    @GET("{id}/videos?api_key=10b603ec4b946df16959d577ebe2f890&language=en-US")
    fun getVideo(@Path("id") id: String) : Call<ResultVideo>

}