package com.example.themoviedb.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.databinding.ItemMovieBinding
import com.example.themoviedb.model.Movie
import com.example.themoviedb.R
import com.example.themoviedb.view.DetailActivity

class MovieAdapter(private var listMovie: List<Movie>)
    :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{
    class MovieViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button)
        val imageView = view.findViewById<ImageView>(R.id.image_view_movie)
        val nameMovie = view.findViewById<TextView>(R.id.text_view_movie)
        val overviewMovie = view.findViewById<TextView>(R.id.text_view_overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            with(listMovie[position]) {
                nameMovie.text = original_title
                overviewMovie.text = overview
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+poster_path)
                    .into(imageView)
                button.setOnClickListener {
                    val context = view.context
                    val intent =Intent(context, DetailActivity::class.java)
                intent.putExtra("id", id.toString())
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = listMovie.size

}