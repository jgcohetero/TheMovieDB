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

    companion object {
        lateinit var apiservice: MovieService
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtenemos la opción seleccionada por el usuario
        val option = intent?.extras?.getString("option").toString()

        // Inicializamos el recyclerView
        recyclerView = binding.recyclerViewMovie
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Creamos una instancia para las consultas
        apiservice =
            RetrofitInstance.getRetrofitInstance().create<MovieService>(MovieService::class.java)


        getMovies(option)
    }


    private fun getMovies(option: String) {

        // Llmamos a nuestro método getMovies y le pasamos la opción del usuario

        apiservice.getMovies(option).enqueue(
            object : Callback<ResultMovie> {
                override fun onResponse(call: Call<ResultMovie>, response: Response<ResultMovie>) {
                    val listMovies = response.body()!!.results

                    // Le pasamos información al MovieAdapter
                    recyclerView.adapter = MovieAdapter(listMovies)
                }

                override fun onFailure(call: Call<ResultMovie>, t: Throwable) {
                    Toast.makeText(this@MovieActivity, "Sin conexión", Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}