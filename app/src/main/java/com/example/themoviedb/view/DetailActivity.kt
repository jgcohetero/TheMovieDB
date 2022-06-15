package com.example.themoviedb.view

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.adapter.VideoAdapter
import com.example.themoviedb.api.MovieService
import com.example.themoviedb.api.RetrofitInstance
import com.example.themoviedb.databinding.ActivityDetailBinding
import com.example.themoviedb.model.Movie
import com.example.themoviedb.model.ResultVideo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperamos el id de la película seleccionada

        val id = intent?.extras?.getString("id").toString()

        recyclerView = binding.recyclerViewVideo
        recyclerView.layoutManager = LinearLayoutManager(this)

        MovieActivity.apiservice = RetrofitInstance.getRetrofitInstance().create<MovieService>(MovieService::class.java)
        getMovieInfo(id)
        getVideoMovie(id)
    }

    private fun getMovieInfo(id: String) {
        // Obtenemos la información de película y mostramos los datos
        MovieActivity.apiservice.getMovieInfo(id).enqueue(
            object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    var movieInfo = response.body()!!

                    Glide.with(this@DetailActivity)
                        .load("https://image.tmdb.org/t/p/w500"+movieInfo.poster_path)
                        .into(binding.imageViewPoster)
                    binding.textViewName.text = movieInfo.original_title
                    binding.textViewLanguage.text = movieInfo.original_language.uppercase()
                    binding.textViewDate.text = movieInfo.release_date
                    binding.textViewOverview.text = movieInfo.overview
                    title = movieInfo.original_title
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Error", Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    private fun getVideoMovie(id: String) {
        // Obtenemos los videos de la película seleccionada y lo pasamos al VideoAdapter
        MovieActivity.apiservice.getVideo(id).enqueue(
            object : Callback<ResultVideo> {
                override fun onResponse(call: Call<ResultVideo>, response: Response<ResultVideo>) {
                    var listVideo = response.body()!!.results
                    val playVideo = this@DetailActivity
                    recyclerView.adapter = VideoAdapter(listVideo, playVideo)
                }

                override fun onFailure(call: Call<ResultVideo>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Error", Toast.LENGTH_LONG).show()
                }


            }
        )
    }


}