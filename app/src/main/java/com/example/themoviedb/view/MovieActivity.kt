package com.example.themoviedb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.adapter.MovieAdapter
import com.example.themoviedb.api.MovieService
import com.example.themoviedb.api.RetrofitInstance
import com.example.themoviedb.databinding.ActivityMoviesBinding
import com.example.themoviedb.model.ResultMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {

    companion object{
        lateinit var apiservice: MovieService
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var option = intent?.extras?.getString("option").toString()
        recyclerView = binding.recyclerViewMovie
        recyclerView.layoutManager = LinearLayoutManager(this)
        apiservice = RetrofitInstance.getRetrofitInstance().create<MovieService>(MovieService::class.java)
        getMovies(option)
        title = option.uppercase()
    }

    private fun getMovies(option: String) {
        apiservice.getMovies(option).enqueue(
            object : Callback<ResultMovie>{
                override fun onResponse(call: Call<ResultMovie>, response: Response<ResultMovie>) {
                    val listMovies = response.body()!!.results
                    recyclerView.adapter = MovieAdapter(listMovies)
                }

                override fun onFailure(call: Call<ResultMovie>, t: Throwable) {
                    Toast.makeText(this@MovieActivity, "Sin conexión", Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}