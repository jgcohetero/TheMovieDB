package com.example.themoviedb.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ActivityOptionBinding

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPopular.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("option", "popular")
            this.startActivity(intent)
        }
        binding.buttonNowPlaying.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("option", "now_playing")
            this.startActivity(intent)
        }
    }
}